/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 1997-2007 Sun Microsystems, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common
 * Development and Distribution License("CDDL") (collectively, the
 * "License"). You may not use this file except in compliance with the
 * License. You can obtain a copy of the License at
 * http://www.netbeans.org/cddl-gplv2.html
 * or nbbuild/licenses/CDDL-GPL-2-CP. See the License for the
 * specific language governing permissions and limitations under the
 * License.  When distributing the software, include this License Header
 * Notice in each file and include the License file at
 * nbbuild/licenses/CDDL-GPL-2-CP.  Sun designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Sun in the GPL Version 2 section of the License file that
 * accompanied this code. If applicable, add the following below the
 * License Header, with the fields enclosed by brackets [] replaced by
 * your own identifying information:
 * "Portions Copyrighted [year] [name of copyright owner]"
 *
 * Contributor(s):
 *
 * The Original Software is NetBeans. The Initial Developer of the Original
 * Software is Sun Microsystems, Inc. Portions Copyright 1997-2006 Sun
 * Microsystems, Inc. All Rights Reserved.
 *
 * If you wish your version of this file to be governed by only the CDDL
 * or only the GPL Version 2, indicate your decision by adding
 * "[Contributor] elects to include this software in this distribution
 * under the [CDDL or GPL Version 2] license." If you do not indicate a
 * single choice of license, a recipient has the option to distribute
 * your version of this file under either the CDDL, the GPL Version 2 or
 * to extend the choice of license to its licensees as provided above.
 * However, if you add GPL Version 2 code and therefore, elected the GPL
 * Version 2 license, then the option applies only if the new code is
 * made subject to such option by the copyright holder.
 */

package org.netbeans.modules.java.tools.navigation;

import java.util.EnumSet;
import java.util.List;
import java.util.Set;

import javax.lang.model.element.Element;
import static javax.lang.model.element.ElementKind.*;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.TypeParameterElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.ArrayType;
import javax.lang.model.type.DeclaredType;
import static javax.lang.model.type.TypeKind.*;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.type.TypeVariable;
import javax.lang.model.type.WildcardType;

/**
 *
 * @author Sandip Chitale (Sandip.Chitale@Sun.Com)
 */
class Utils {
    static String format(Element element) {
        return format(element, false);
    }

    static String format(Element element, boolean forSignature) {
        StringBuilder stringBuilder = new StringBuilder();
        format(element, stringBuilder, forSignature);

        return stringBuilder.toString();
    }

    static void format(Element element, StringBuilder stringBuilder, boolean forSignature) {
        if (element == null) {
            return;
        }

        boolean first = true;
        Set<Modifier> modifiers = element.getModifiers();

        switch (element.getKind()) {
        case PACKAGE:

            PackageElement packageElement = (PackageElement) element;
            stringBuilder.append("package " + packageElement.getQualifiedName());

            break;

        case CLASS:
        case INTERFACE:
        case ENUM:
            if (forSignature) {
                stringBuilder.append(toString(modifiers));
                if (modifiers.size() > 0) {
                    if (stringBuilder.length() > 0) {
                        stringBuilder.append(" ");
                    }
                }
            }
            
            switch (element.getKind()) {
                case CLASS:
                    stringBuilder.append("class ");
                    break;
                case INTERFACE:
                    stringBuilder.append("interface ");
                    break;
                case ENUM:
                    stringBuilder.append("enum ");
                    break;
            }
            
            TypeElement typeElement = (TypeElement) element;
            stringBuilder.append(JavaStructureOptions.isShowFQN()
                ? typeElement.getQualifiedName().toString()
                : typeElement.getSimpleName().toString());

            formatTypeParameters(typeElement.getTypeParameters(), stringBuilder);

            if (!forSignature) {
                if (modifiers.size() > 0) {
                    stringBuilder.append(" : ");
                    stringBuilder.append(toString(modifiers));
                }
            }

            break;

        case CONSTRUCTOR:
            if (forSignature) {
                stringBuilder.append(toString(modifiers));
                if (modifiers.size() > 0) {
                    if (stringBuilder.length() > 0) {
                        stringBuilder.append(" ");
                    }
                }
            }

            ExecutableElement constructorElement = (ExecutableElement) element;
            stringBuilder.append(constructorElement.getEnclosingElement()
                                                   .getSimpleName().toString());
            stringBuilder.append("(");
            formatVariableElements(constructorElement.getParameters(),
                constructorElement.isVarArgs(), stringBuilder);
            stringBuilder.append(")");

            List<? extends TypeMirror> thrownTypesMirrors = constructorElement.getThrownTypes();
            if (!thrownTypesMirrors.isEmpty()) {
                stringBuilder.append(" throws ");
                formatTypeMirrors(thrownTypesMirrors, stringBuilder);
            }
            if (!forSignature) {
                if (modifiers.size() > 0) {
                    stringBuilder.append(":");
                    stringBuilder.append(toString(modifiers));
                }
            }

            break;

        case METHOD:
            ExecutableElement methodElement = (ExecutableElement) element;
            TypeMirror returnTypeMirror = methodElement.getReturnType();
            List<?extends TypeParameterElement> typeParameters = methodElement.getTypeParameters();

            if (forSignature) {
                stringBuilder.append(toString(modifiers));
                
                if (modifiers.size() > 0) {
                    if (stringBuilder.length() > 0) {
                        stringBuilder.append(" ");
                    }
                }

                if ((typeParameters != null) && (typeParameters.size() > 0)) {
                    formatTypeParameters(typeParameters, stringBuilder);
                    if (stringBuilder.length() > 0) {
                        stringBuilder.append(" ");
                    }
                }



                formatTypeMirror(returnTypeMirror, stringBuilder);
            }

            if (stringBuilder.length() > 0) {
                    stringBuilder.append(" ");
            }

            stringBuilder.append(methodElement.getSimpleName().toString());
            stringBuilder.append("(");
            formatVariableElements(methodElement.getParameters(),
                methodElement.isVarArgs(), stringBuilder);
            stringBuilder.append(")");

            List<? extends TypeMirror> thrownTypesMirrorsByMethod = methodElement.getThrownTypes();
            if (!thrownTypesMirrorsByMethod.isEmpty()) {
                stringBuilder.append(" throws ");
                formatTypeMirrors(thrownTypesMirrorsByMethod, stringBuilder);
            }

            if (!forSignature) {
                stringBuilder.append(":");

                if (modifiers.size() > 0) {
                    stringBuilder.append(toString(modifiers));
                    if (modifiers.size() > 0) {
                        stringBuilder.append(" ");
                    }
                }

                formatTypeMirror(returnTypeMirror, stringBuilder);

                if ((typeParameters != null) && (typeParameters.size() > 0)) {
                    stringBuilder.append(":");
                    formatTypeParameters(typeParameters, stringBuilder);
                }

                if (JavaStructureOptions.isShowInherited()) {
                    stringBuilder.append(" [");
                    stringBuilder.append(element.getEnclosingElement());
                    stringBuilder.append("]");
                }
            }

            break;

        case TYPE_PARAMETER:
            TypeParameterElement typeParameterElement = (TypeParameterElement) element;
            stringBuilder.append(typeParameterElement.getSimpleName());

            List<?extends TypeMirror> bounds = null;
            try {
                bounds = typeParameterElement.getBounds();
                if ((bounds != null) && (bounds.size() > 0)) {
                    if (bounds.size() == 1 && "java.lang.Object".equals( bounds.get(0).toString())) {
                    } else {
                        stringBuilder.append(" extends ");
                        first = true;
                        for (TypeMirror typeMirror : bounds) {
                            if (first) {
                                first = false;
                            } else {
                                stringBuilder.append(" & ");
                            }
                            formatTypeMirror(typeMirror, stringBuilder);
                        }
                    }
                }
            } catch (NullPointerException npe) {
                // Bug?
            }

            break;

        case FIELD:
            VariableElement fieldElement = (VariableElement) element;
            if (forSignature) {
                stringBuilder.append(toString(modifiers));

                if (stringBuilder.length() > 0) {
                    stringBuilder.append(" ");
                }

                formatTypeMirror(fieldElement.asType(), stringBuilder);
            }

            if (stringBuilder.length() > 0) {
                stringBuilder.append(" ");
            }

            stringBuilder.append(fieldElement.getSimpleName().toString());

            if (!forSignature) {
                stringBuilder.append(":");
                if (modifiers.size() > 0) {
                    stringBuilder.append(toString(modifiers));
                }

                if (stringBuilder.length() > 0) {
                    stringBuilder.append(" ");
                }

                formatTypeMirror(fieldElement.asType(), stringBuilder);

                if (JavaStructureOptions.isShowInherited()) {
                    stringBuilder.append(" [");
                    stringBuilder.append(element.getEnclosingElement());
                    stringBuilder.append("]");
                }
            }

            break;

        case ENUM_CONSTANT:
            stringBuilder.append(element.toString());

            if (modifiers.size() > 0) {
                stringBuilder.append(":");
                stringBuilder.append(toString(modifiers));
            }

            if (JavaStructureOptions.isShowInherited()) {
                stringBuilder.append(" [");
                stringBuilder.append(element.getEnclosingElement());
                stringBuilder.append("]");
            }

            break;

        case PARAMETER:
            VariableElement variableElement = (VariableElement) element;
            formatTypeMirror(variableElement.asType(), stringBuilder);
            stringBuilder.append(" ");
            stringBuilder.append(element.getSimpleName().toString());

            break;
        }
    }

    static void formatTypeMirror(TypeMirror typeMirror,
        StringBuilder stringBuilder) {
        if (typeMirror == null) {
            return;
        }

        boolean first = true;

        switch (typeMirror.getKind()) {
        case BOOLEAN:
        case BYTE:
        case CHAR:
        case DOUBLE:
        case FLOAT:
        case INT:
        case LONG:
        case NONE:
        case NULL:
        case SHORT:
        case VOID:
            stringBuilder.append(typeMirror);

            break;

        case TYPEVAR:
            TypeVariable typeVariable = (TypeVariable)typeMirror;
            stringBuilder.append(typeVariable.asElement().getSimpleName().toString());
            break;

        case WILDCARD:
            WildcardType wildcardType = (WildcardType)typeMirror;
            stringBuilder.append("?");
            if ( wildcardType.getExtendsBound() != null ) {
                stringBuilder.append(" extends "); // NOI18N
                formatTypeMirror(wildcardType.getExtendsBound(), stringBuilder);
            }
            if ( wildcardType.getSuperBound() != null ) {
                stringBuilder.append(" super "); // NOI18N
                formatTypeMirror(wildcardType.getSuperBound(), stringBuilder);
            }

            break;

        case DECLARED:
            DeclaredType declaredType = (DeclaredType) typeMirror;
            Element element = declaredType.asElement();
            if (element instanceof TypeElement) {
                stringBuilder.append(
                    JavaStructureOptions.isShowFQN() ?
                    ((TypeElement)element).getQualifiedName().toString() :
                    element.getSimpleName().toString());
            } else {
                stringBuilder.append(element.getSimpleName().toString());
            }
            List<? extends TypeMirror> typeArgs = declaredType.getTypeArguments();
            if ( !typeArgs.isEmpty() ) {
                stringBuilder.append("<");
                formatTypeMirrors(typeArgs, stringBuilder);
                stringBuilder.append(">");
            }

            break;

        case ARRAY:

            int dims = 0;

            while (typeMirror.getKind() == ARRAY) {
                dims++;
                typeMirror = ((ArrayType) typeMirror).getComponentType();
            }

            formatTypeMirror(typeMirror, stringBuilder);

            for (int i = 0; i < dims; i++) {
                stringBuilder.append("[]");
            }

            break;
        }
    }

    static void formatTypeParameters(
        List<? extends TypeParameterElement> typeParameters,
        StringBuilder stringBuilder) {
        if ((typeParameters == null) || (typeParameters.size() == 0)) {
            return;
        }

        boolean first = true;

        if (typeParameters.size() > 0) {
            stringBuilder.append("<");
            first = true;

            for (TypeParameterElement typeParameterElement : typeParameters) {
                if (first) {
                    first = false;
                } else {
                    stringBuilder.append(", ");
                }

                format(typeParameterElement, stringBuilder, false);
            }

            stringBuilder.append(">");
        }
    }

    static void formatVariableElements(
        List<? extends VariableElement> variableElements, boolean varArgs,
        StringBuilder stringBuilder) {
        if ((variableElements == null) || (variableElements.size() == 0)) {
            return;
        }

        boolean first = true;

        for (VariableElement variableElement : variableElements) {
            if (first) {
                first = false;
            } else {
                stringBuilder.append(", ");
            }

           format(variableElement, stringBuilder, false);
        }

        if (varArgs) {
            stringBuilder.append("...");
        }
    }

    static void formatTypeMirrors(List<?extends TypeMirror> thrownTypeMirros,
        StringBuilder stringBuilder) {
        if ((thrownTypeMirros == null) || (thrownTypeMirros.size() == 0)) {
            return;
        }

        boolean first = true;

        for (TypeMirror typeMirror : thrownTypeMirros) {
            if (first) {
                first = false;
            } else {
                stringBuilder.append(", ");
            }

            formatTypeMirror(typeMirror, stringBuilder);
        }
    }

    static int getIntModifiers(Set<Modifier> modifiers) {
        int intModifiers = 0;

        if (modifiers.contains(Modifier.ABSTRACT)) {
            intModifiers |= java.lang.reflect.Modifier.ABSTRACT;
        }

        if (modifiers.contains(Modifier.FINAL)) {
            intModifiers |= java.lang.reflect.Modifier.FINAL;
        }

        if (modifiers.contains(Modifier.NATIVE)) {
            intModifiers |= java.lang.reflect.Modifier.NATIVE;
        }

        if (modifiers.contains(Modifier.PRIVATE)) {
            intModifiers |= java.lang.reflect.Modifier.PRIVATE;
        }

        if (modifiers.contains(Modifier.PROTECTED)) {
            intModifiers |= java.lang.reflect.Modifier.PROTECTED;
        }

        if (modifiers.contains(Modifier.PUBLIC)) {
            intModifiers |= java.lang.reflect.Modifier.PUBLIC;
        }

        if (modifiers.contains(Modifier.STATIC)) {
            intModifiers |= java.lang.reflect.Modifier.STATIC;
        }

        if (modifiers.contains(Modifier.STRICTFP)) {
            intModifiers |= java.lang.reflect.Modifier.STRICT;
        }

        if (modifiers.contains(Modifier.SYNCHRONIZED)) {
            intModifiers |= java.lang.reflect.Modifier.SYNCHRONIZED;
        }

        if (modifiers.contains(Modifier.TRANSIENT)) {
            intModifiers |= java.lang.reflect.Modifier.TRANSIENT;
        }

        if (modifiers.contains(Modifier.VOLATILE)) {
            intModifiers |= java.lang.reflect.Modifier.VOLATILE;
        }

        return intModifiers;
    }

    static String toString(Set<Modifier> modifiers) {
        return java.lang.reflect.Modifier.toString(getIntModifiers(modifiers));
    }

    static Set<Modifier> getModifiers(int intModifiers) {
        EnumSet<Modifier> modifiers = EnumSet.noneOf(Modifier.class);

        if ((intModifiers & java.lang.reflect.Modifier.ABSTRACT) != 0) {
            modifiers.add(Modifier.ABSTRACT);
        }

        if ((intModifiers & java.lang.reflect.Modifier.FINAL) != 0) {
            modifiers.add(Modifier.FINAL);
        }

        if ((intModifiers & java.lang.reflect.Modifier.NATIVE) != 0) {
            modifiers.add(Modifier.NATIVE);
        }

        if ((intModifiers & java.lang.reflect.Modifier.PRIVATE) != 0) {
            modifiers.add(Modifier.PRIVATE);
        }

        if ((intModifiers & java.lang.reflect.Modifier.PROTECTED) != 0) {
            modifiers.add(Modifier.PROTECTED);
        }

        if ((intModifiers & java.lang.reflect.Modifier.PUBLIC) != 0) {
            modifiers.add(Modifier.PUBLIC);
        }

        if ((intModifiers & java.lang.reflect.Modifier.STATIC) != 0) {
            modifiers.add(Modifier.STATIC);
        }

        if ((intModifiers & java.lang.reflect.Modifier.STRICT) != 0) {
            modifiers.add(Modifier.STRICTFP);
        }

        if ((intModifiers & java.lang.reflect.Modifier.SYNCHRONIZED) != 0) {
            modifiers.add(Modifier.SYNCHRONIZED);
        }

        if ((intModifiers & java.lang.reflect.Modifier.TRANSIENT) != 0) {
            modifiers.add(Modifier.TRANSIENT);
        }

        if ((intModifiers & java.lang.reflect.Modifier.VOLATILE) != 0) {
            modifiers.add(Modifier.VOLATILE);
        }

        return modifiers;
    }
}
