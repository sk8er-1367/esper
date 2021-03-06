/*
 ***************************************************************************************
 *  Copyright (C) 2006 EsperTech, Inc. All rights reserved.                            *
 *  http://www.espertech.com/esper                                                     *
 *  http://www.espertech.com                                                           *
 *  ---------------------------------------------------------------------------------- *
 *  The software in this package is published under the terms of the GPL license       *
 *  a copy of which has been included with this distribution in the license.txt file.  *
 ***************************************************************************************
 */
package com.espertech.esper.codegen.model.statement;

import com.espertech.esper.codegen.base.CodegenBlock;
import com.espertech.esper.codegen.core.CodegenIndent;
import com.espertech.esper.codegen.model.expression.CodegenExpression;

import java.util.Map;
import java.util.Set;

public class CodegenStatementForIntSimple extends CodegenStatementWBlockBase {
    private final String ref;
    private final CodegenExpression upperLimit;
    private CodegenBlock block;

    public CodegenStatementForIntSimple(CodegenBlock parent, String ref, CodegenExpression upperLimit) {
        super(parent);
        this.ref = ref;
        this.upperLimit = upperLimit;
    }

    public void setBlock(CodegenBlock block) {
        this.block = block;
    }

    public void render(StringBuilder builder, Map<Class, String> imports, boolean isInnerClass, int level, CodegenIndent indent) {
        builder.append("for (int ").append(ref).append("=0; ").append(ref).append("<");
        upperLimit.render(builder, imports, isInnerClass);
        builder.append("; ").append(ref).append("++) {\n");
        block.render(builder, imports, isInnerClass, level + 1, indent);
        indent.indent(builder, level);
        builder.append("}\n");
    }

    public void mergeClasses(Set<Class> classes) {
        block.mergeClasses(classes);
        upperLimit.mergeClasses(classes);
    }
}
