package org.example.bankingportal.Util;

import io.micrometer.common.lang.Nullable;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.jcip.annotations.GuardedBy;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

@Slf4j
public class JwtAuthenticationForAuthorities<T> implements Converter<Jwt, Collection<? extends GrantedAuthority>> {


    private String claimName;

    private final Collection<String> WELL_KNOW_CLAIM_NAMES = List.of("scope", "scp");

    private final transient Object lock = new Object();

    @Setter
    private Object[] objects = new Object[0];

    @Override
    public Collection<? extends GrantedAuthority> convert(@Nullable Jwt jwt) {
        Collection<SimpleGrantedAuthority> authoritiesList = new HashSet<>();

        String var4;
        for (String object : this.getAuthorities(jwt)) {
            var4 = object;
            String SCOPE_PREFIX = "SCOPE_";
            authoritiesList.add(new SimpleGrantedAuthority(SCOPE_PREFIX + var4));
        }
        return authoritiesList;
    }

    @SuppressWarnings("unchecked")
    private Collection<String> getAuthorities(Jwt jwt) {
        String claimAuthorities = this.getAuthoritiesAsClaims(jwt);
        if (claimAuthorities == null) {
            log.info("can't find list of authorities with claimName {} ", claimName);
            return Collections.emptyList();
        } else {
            Object claims = jwt.getClaim(claimName);
            if (claims instanceof String s) { // pattern variable
                return StringUtils.hasText(s) ? Arrays.asList(s.split(",")) : Collections.emptyList();
            } else {
                if (claims instanceof Collection) {
                    return Collections.unmodifiableCollection((Collection<String>) claims);
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

    @SuppressWarnings("unchecked")
    public T[] toArray(T[] arr) {
        final Collection<? extends T> collection = new CopyOnWriteArrayList<>(arr);

        T[] array = collection.toArray(arr.length == 0 ? arr : Arrays.copyOf(arr, 0));
        for (int i = 0; i < array.length; i++) {
            arr[i] = (T) new ArrayList<>((Collection<T>) array[i]);
        }

        if (array.length > arr.length) {
            return array;
        }

        System.arraycopy(array, 0, arr, 0, array.length);
        if (arr.length > array.length) {
            arr[array.length] = null;
        }


        return arr;
    }

    public <T> T elementAt(T[] arr, int index) {
        return arr[index];
    }

    @SuppressWarnings("unchecked")
    public T[] getArray() {
        return (T[]) this.objects;
    }

    public void insertAtIndex(int index, T element) {
        T[] array = this.getArray(); // get the specified array
        T oldValue = this.elementAt(array, index);

        if (oldValue != null) {
            array = array.clone();
            array[index] = oldValue;
        }
        array[index] = element;


    }

    //copyOnWrite exercise

    @SuppressWarnings("unchecked")
    public void add(int index, T element) {
        synchronized (this.lock) {
            T[] array = this.getArray();
            int length = array.length;
            T[] elements;
            if (index > length || index < 0) {
                throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + length);
            }
            int arrLen = length - index;
            if (arrLen == 0) {
                elements = Arrays.copyOf(array, length + 1);
            } else {
                elements = (T[]) new Object[length + 1];
                System.arraycopy(array, 0, elements, 0, index); // first array index
                System.arraycopy(array, index, elements, index + 1, arrLen);

            }
            elements[index] = element;
        }
    }


}
