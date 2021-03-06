

package org.netbeans.modules.fortranmodel;

import java.util.List;

/**
 * Represents a scope:global (a main program section, module, 
 * or external subprogram), local(internal subprogram)
 * @author Andrey Gubichev
 */
public interface FScope extends FObject{
        List<FScopeElement> getScopeElements();
}
