/*
 * Copyright 2012 JBoss Inc
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.drools.guvnor.models.commons.shared.imports;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Imports {

    private ArrayList<Import> imports = new ArrayList<Import>();

    public Imports() {
    }

    public Imports( final List<Import> imports ) {
        this.imports = new ArrayList<Import>( imports );
    }

    public List<Import> getImports() {
        return imports;
    }

    public String toString() {
        final StringBuilder sb = new StringBuilder();
        for ( final Import i : imports ) {
            sb.append( "import " ).append( i.getType() ).append( ";\n" );
        }

        return sb.toString();
    }

    public void addImport( final Import item ) {
        imports.add( item );
    }

    public void removeImport( final Import item ) {
        final Iterator<Import> itr = imports.iterator();
        while ( itr.hasNext() ) {
            final Import i = itr.next();
            if ( i.getType().equals( item.getType() ) ) {
                itr.remove();
                break;
            }
        }
    }

    public boolean contains( final Import item ) {
        for ( Import i : imports ) {
            if ( i.getType().equals( item.getType() ) ) {
                return true;
            }
        }
        return false;
    }

}
