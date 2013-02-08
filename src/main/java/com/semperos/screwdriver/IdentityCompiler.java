package com.semperos.screwdriver;

/**
 * This "compiler" returns the identity of what it receives.
 *
 * This may also be a place to do other non-invasive things
 * to assets that just need to be copied. Here now
 * for consistency with other compilers when no
 * acceptable compiler is found for a given asset.
 *
 */
public class IdentityCompiler {
    public String compile(String sourceCode) {
        return sourceCode;
    }
}
