package cn.sixlab.minesoft.singte.core.common.vo;

import cn.sixlab.minesoft.singte.core.common.utils.StConst;
import cn.sixlab.minesoft.singte.core.models.StUser;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.*;

public class StUserDetails implements UserDetails, CredentialsContainer {
    private final StUser stUser;
    private final String username;
    private String password;
    private final Set<GrantedAuthority> authorities;

    private final boolean enabled;

    public StUserDetails(StUser stUser) {
        List<GrantedAuthority> authorityList = new ArrayList<>();
        authorityList.add(new SimpleGrantedAuthority(stUser.getRole()));

        this.stUser = stUser;
        this.username = stUser.getUsername();
        this.password = stUser.getPassword();
        this.enabled = StConst.YES.equals(stUser.getStatus());
        this.authorities = Collections.unmodifiableSet(sortAuthorities(authorityList));
    }

    public Collection<GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    public StUser getStUser() {
        return stUser;
    }

    public String getPassword() {
        return this.password;
    }

    public String getUsername() {
        return this.username;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public boolean isAccountNonExpired() {
        return true;
    }

    public boolean isAccountNonLocked() {
        return true;
    }

    public boolean isCredentialsNonExpired() {
        return true;
    }

    public void eraseCredentials() {
        this.password = null;
        this.stUser.setPassword(null);
    }

    private static SortedSet<GrantedAuthority> sortAuthorities(Collection<? extends GrantedAuthority> authorities) {
        SortedSet<GrantedAuthority> sortedAuthorities = new TreeSet<>(new StUserDetails.AuthorityComparator());

        sortedAuthorities.addAll(authorities);

        return sortedAuthorities;
    }

    public boolean equals(Object obj) {
        return obj instanceof StUserDetails && this.username.equals(((StUserDetails) obj).username);
    }

    public int hashCode() {
        return this.username.hashCode();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getClass().getName()).append(" [");
        sb.append("Username=").append(this.username).append(", ");
        sb.append("Password=[PROTECTED], ");
        sb.append("Enabled=").append(this.enabled).append(", ");
        sb.append("AccountNonExpired=true,");
        sb.append("credentialsNonExpired=true,");
        sb.append("AccountNonLocked=true,");
        sb.append("Granted Authorities=").append(this.authorities).append("]");
        return sb.toString();
    }

    private static class AuthorityComparator implements Comparator<GrantedAuthority>, Serializable {

        public int compare(GrantedAuthority g1, GrantedAuthority g2) {
            if (g2.getAuthority() == null) {
                return -1;
            }
            if (g1.getAuthority() == null) {
                return 1;
            }
            return g1.getAuthority().compareTo(g2.getAuthority());
        }
    }
}
