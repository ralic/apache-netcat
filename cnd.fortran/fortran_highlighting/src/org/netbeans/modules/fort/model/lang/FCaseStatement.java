

package org.netbeans.modules.fort.model.lang;

/**
 * Represents case  statement :"case...:"
 * @author Andrey Gubichev
 */
public interface FCaseStatement extends FStatement{
    /** return expression after "case"*/
    FExpression getExpression();
}
