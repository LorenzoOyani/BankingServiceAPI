package org.example.bankingportal.java21;

import org.springframework.core.convert.TypeDescriptor;
import org.springframework.security.oauth2.core.converter.ClaimConversionService;
import org.springframework.util.Assert;

import java.lang.annotation.Annotation;
import java.net.URL;
import java.util.Map;

public interface ClaimsAccessors {

    Map<String, Object> claims = Map.of();

   Annotation[] annotations = new Annotation[0];


    default Map<String, Object> getClaims() {
        return claims;
    }

    default boolean hasClaims(String claimName) {
        Assert.notNull(claims, "claims must not be null");
        return !this.getClaims().containsKey(claimName);
    }

    default Object getClaim(String claimName) {
        Assert.notNull(claims, "claims must not be null");
        return this.getClaims().get(claimName);
    }

    default boolean getClaimBoolean(String claimName) {
        if (this.hasClaims(claimName)) {
            return false;
        } else {
            Object valueClaims = this.getClaim(claimName);
            Boolean value = (Boolean) ClaimConversionService.getSharedInstance().convert(valueClaims, Boolean.class);
            Assert.notNull(value, () ->
                    "unable to convert " + claimName + " to Boolean"
            );

            return value;
        }
    }


    default URL getClaimUrl(String claimName) {
        if(hasClaims(claimName)){
            return null;
        }else{
            Object valueClaims = this.getClaim(claimName);
            URL value = (URL) ClaimConversionService.getSharedInstance().convert(valueClaims, URL.class);
            Assert.notNull(value, ()-> "unable to convert " + claimName + " to URL");

            return value;
        }
    }

    @SuppressWarnings("unchecked")
    default Map<String, Object> getClaimsAsMap(String claimName) {
        if(hasClaims(claimName)){
            return null;
        }else{
            TypeDescriptor sourceDescriptor = TypeDescriptor.valueOf(Object.class);
            TypeDescriptor targetDescriptor = TypeDescriptor.map(Map.class, TypeDescriptor.valueOf(String.class), TypeDescriptor.valueOf(Object.class));
            Map<String, Object> valueClaims = (Map<String,Object>) ClaimConversionService.getSharedInstance().convert(claims, sourceDescriptor, targetDescriptor);
            Assert.notNull(valueClaims, () ->
                    "unable to convert " + claimName + " to Map");

            return valueClaims;
        }

    }

    @SuppressWarnings("unchecked")
    default <T extends Annotation> T getAnnotations(Class<T> annotationClass) {
        Annotation[] annotations = ClaimsAccessors.annotations;
        for (Annotation annotation : annotations) {
            if (annotation.annotationType().equals(annotationClass)) {
                return (T) annotation;
            }
        }
        return null;
    }


}
