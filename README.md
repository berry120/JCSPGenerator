[![Codacy Badge](https://api.codacy.com/project/badge/Grade/3252efe6e79b4ce7b249222634cf9aed)](https://www.codacy.com/app/berry120/JCSPGenerator?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=berry120/JCSPGenerator&amp;utm_campaign=Badge_Grade)
[![Codacy Badge](https://api.codacy.com/project/badge/Coverage/3252efe6e79b4ce7b249222634cf9aed)](https://www.codacy.com/app/berry120/JCSPGenerator?utm_source=github.com&utm_medium=referral&utm_content=berry120/JCSPGenerator&utm_campaign=Badge_Coverage)
[![CircleCI](https://circleci.com/gh/berry120/JCSPGenerator.svg?style=svg)](https://circleci.com/gh/berry120/JCSPGenerator)
[![MIT](https://img.shields.io/badge/license-MIT-green.svg)](https://opensource.org/licenses/MIT)
[![Mutation tested with PIT](https://img.shields.io/badge/-Mutation%20tested%20with%20PIT-blue.svg)](http://pitest.org/)

# JCSPGenerator
JCSPGenerator is an open source Java library for dynamically generating a `Content-Security-Policy` header. Requires Java 8+.

**Please be very careful if using this library in a production setting.**

Setting an incorrect, or even partially incorrect CSP header can easily hamper your site's performance and security, potentially rendering it both unusable and vulnerable. Only use this library if you have the knowledge & means to thoroughly, independently test any value it produces.

Basic usage:

    CSPHeader header = new CSPHeader(
            CSP.defaultSrc(CSP.SELF, "https://res.example.com"),
            CSP.frameSrc(CSP.NONE),
            CSP.imgSrc(CSP.SELF, "https://images.example.com", "https://cdn.example.com"),
            CSP.mediaSrc(CSP.NONE),
            CSP.objectSrc(CSP.NONE),
            CSP.scriptSrc(CSP.SELF),
            CSP.frameAncestors(CSP.NONE),
            CSP.blockAllMixedContent(),
    );
    System.out.println(header.getValue());

A slightly more advanced use case:

    String nonce = CSPUtils.generateNonce();
    CSPHeader header = new CSPHeader(
            CSP.defaultSrc(CSP.SELF),
            CSP.connectSrc(CSP.SELF, "https://legacy.example.com"),
            CSP.fontSrc(CSP.SELF, "https://fonts.example.com"),
            CSP.frameSrc(CSP.SELF, "https://frame.paypal.com", "https://frame2.paypal.com", "https://analytics.provider.info"),
            CSP.imgSrc(CSP.SELF, "https://images.example.com", "https://cdn.example.com"),
            CSP.mediaSrc(CSP.NONE),
            CSP.objectSrc(CSP.NONE),
            CSP.scriptSrc(CSP.SELF, CSP.nonce(nonce)),
            CSP.frameAncestors(CSP.NONE),
            CSP.blockAllMixedContent(),
            CSP.upgradeInsecureRequests()
    );
    System.out.println(header.getValue()); //Put this as the value of your Content-Security-Policy header
    header.getLegacyXFrameOptionsValue().ifPresent(System.out::println); //Print out a meaningful, legacy X-Frame-Options equivalent if possible
    
Integrates with Spring easily:

    @Override
    protected void configure(HttpSecurity http) throws Exception {
      http.headers()
          .contentSecurityPolicy(header.getValue());
    }
  
**Maven and Gradle examples coming soon.**

Any feature requests, issues or suggested improvements then please file an issue and/or PR.


