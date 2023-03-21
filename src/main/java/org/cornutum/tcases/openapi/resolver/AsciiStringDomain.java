//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.cornutum.tcases.openapi.resolver;

import java.util.stream.Stream;
import org.cornutum.tcases.openapi.Characters;
import org.cornutum.tcases.openapi.Characters.Ascii;
import org.cornutum.tcases.openapi.resolver.AbstractStringDomain.PatternResolver;

public class AsciiStringDomain extends AbstractStringDomain {
    private final String allowedChars_;

    public AsciiStringDomain() {
        this(Characters.ASCII);
    }

    public AsciiStringDomain(int maxLength) {
        this(maxLength, Characters.ASCII);
    }

    public AsciiStringDomain(Characters chars) {
        this(256, chars);
    }

    public AsciiStringDomain(int maxLength, Characters chars) {
        super(maxLength, chars);
        this.allowedChars_ = (String)chars.filtered(Ascii.chars()).orElseThrow(() -> {
            return new ValueDomainException(String.format("Character set=%s does not accept any ASCII characters", chars));
        });
    }

    protected Stream<String> matchingCandidates(ResolverContext context, PatternResolver patternResolver) {
        return this.generateMatchingValues(context, patternResolver);
    }

    protected String newValue(ResolverContext context, int length) {
        StringBuilder value = new StringBuilder();

        for(int i = 0; i < length; ++i) {
            value.append(this.allowedChars_.charAt(context.getRandom().nextInt(this.allowedChars_.length())));
        }

        return value.toString();
    }
}
