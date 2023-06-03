/*

Grupo

Nome: André Dias Nunes
Matrícula: 201665570C

Nome: Guilherme Barbosa
Matrícula: 201435031

*/

package lang.ast;

import lang.visitors.Visitable;

public abstract class SuperNode implements Visitable {
    public SuperNode() { }
    public abstract int getLine();
    public abstract int getColumn();
}


