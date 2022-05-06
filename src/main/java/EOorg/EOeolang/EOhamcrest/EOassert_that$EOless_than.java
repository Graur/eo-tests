/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2022 Graur Andrew
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
// @checkstyle PackageNameCheck (1 line)
package EOorg.EOeolang.EOhamcrest;

import org.eolang.*;

/**
 * Assert-that.less-than.
 *
 * @since 0.1
 * @checkstyle TypeNameCheck (100 lines)
 */
@SuppressWarnings("PMD.AvoidDollarSigns")
public class EOassert_that$EOless_than extends PhDefault {

    /**
     * Ctor.
     * @param sigma The \sigma
     * @checkstyle BracketsStructureCheck (200 lines)
     */
    @SuppressWarnings("PMD.ConstructorOnlyInitializesOrCallOtherConstructors")
    public EOassert_that$EOless_than(final Phi sigma) {
        super(sigma);
        this.add("x", new AtFree());
        this.add("φ", new AtComposite(this, rho -> {
            final Phi parent = rho.attr("σ").get();
            final Long actual = new Param(parent, "actual").strong(Long.class);
            final String reason = new Param(parent, "reason").strong(String.class);
            final Long expected = new Dataized(rho.attr("x").get()).take(Long.class);
            boolean result = actual < expected;
            Phi[] results;
            try {
                results = new Dataized(parent.attr("results").get()).take(Phi[].class);
                final Phi[] dest = new Phi[results.length + 1];
                System.arraycopy(results, 0, dest, 0, results.length);
                dest[results.length] = new Data.ToPhi(result);
                results = dest;
            } catch (final ExAbstract ex) {
                results = new Phi[]{new Data.ToPhi(result)};
            }
            Phi assertThat = new PhWith(
                    new PhWith(
                            new EOassert_that(Phi.Φ),
                            "reason",
                            new Data.ToPhi(reason)
                    ),
                    "actual",
                    new Data.ToPhi(actual)
            );

            new Dataized(new PhWith(
                    new PhMethod(
                            assertThat, "set-results"
                    ),
                    0,
                    new Data.ToPhi(results)
            )).take();

            return assertThat;
        }));
    }
}
