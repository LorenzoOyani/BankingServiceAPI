package org.example.bankingportal.Util;

import io.micrometer.common.lang.Nullable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.util.StringUtils;

import java.util.*;

@Slf4j
public class JwtAuthenticationForAuthorities implements Converter<Jwt, Collection<? extends GrantedAuthority>> {


    private String claimName;

    private final Collection<String> WELL_KNOW_CLAIM_NAMES = List.of("scope", "scp");

    @Override
    public Collection<? extends GrantedAuthority> convert(@Nullable Jwt jwt) {
        Collection<SimpleGrantedAuthority> authoritiesList = new HashSet<>();

        Object var4;
        for (Object object : this.getAuthorities(jwt)) {
            var4 = object;
            String SCOPE_PREFIX = "SCOPE_";
            authoritiesList.add(new SimpleGrantedAuthority(SCOPE_PREFIX + var4));
        }
        return authoritiesList;
    }

    private Collection<?> getAuthorities(Jwt jwt) {
        String claimAuthorities = this.getAuthoritiesAsClaims(jwt);
        if (claimAuthorities == null) {
            log.info("can't find list of authorities with claimName {} ", claimName);
            return Collections.emptyList();
        } else {
            Object claims = jwt.getClaim(claimName);
            if (claims instanceof String s) { // pattern variable
                return StringUtils.hasText((String) s) ? Arrays.asList(s.split(",")) : Collections.emptyList();
            } else {
                if (claims instanceof Collection) {
                    return Collections.unmodifiableCollection((Collection<?>) claims);
                }
            }
        }
        return Collections.emptyList();
    }

    private String getAuthoritiesAsClaims(Jwt jwt) {
        if (this.claimName == null) {
            return null;
        } else {
            Iterator<String> var4 = this.WELL_KNOW_CLAIM_NAMES.iterator();
            String claims;
            do {
                if (!var4.hasNext()) {
                    throw new IllegalArgumentException("Claim name is missing");
                }
                claims = var4.next();
            } while (jwt.hasClaim(claims));
        }
        return this.claimName;
    }
}
