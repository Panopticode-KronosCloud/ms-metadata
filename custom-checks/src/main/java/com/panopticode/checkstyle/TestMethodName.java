/*
 * java-spring-boot-ms-template
 * Copyright (c) 2024-2026 Panopticode.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.panopticode.checkstyle;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import java.util.regex.Pattern;

public class TestMethodName
    extends AbstractCheck
{
    private static final int[] TOKENS = new int[]{TokenTypes.METHOD_DEF};

    private String _format = "^test[A-Z][a-zA-Z0-9]+$";
    private Pattern _methodPattern = Pattern.compile(_format);

    public void setFormat(String format)
    {
        _format = format;
        // Recompile pattern
        _methodPattern = Pattern.compile(_format);
    }

    @Override
    public int[] getDefaultTokens()
    {
        return TOKENS;
    }

    @Override
    public int[] getAcceptableTokens()
    {
        return TOKENS;
    }

    @Override
    public int[] getRequiredTokens()
    {
        return TOKENS;
    }

    @Override
    public void visitToken(DetailAST ast)
    {
        DetailAST modifiers = ast.findFirstToken(TokenTypes.MODIFIERS);

        if (modifiers != null)
        {
            DetailAST annotations = modifiers.findFirstToken(TokenTypes.ANNOTATION);

            if (annotations != null)
            {
                DetailAST name = annotations.findFirstToken(TokenTypes.IDENT);

                if (name != null && name.getText().equals("Test"))
                {
                    String methodName = ast.findFirstToken(TokenTypes.IDENT).getText();

                    if (!_methodPattern.matcher(methodName).matches())
                    {
                        log(ast.getLineNo(), "panopticode.name.invalidPattern", methodName, _format);
                    }
                }
            }
        }
    }
}
