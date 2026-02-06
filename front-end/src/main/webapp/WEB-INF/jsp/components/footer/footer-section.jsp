<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<spring:eval expression="@environment.getProperty('app.front.public.path')" var="frontPublicPath"/>
<spring:eval expression="@environment.getProperty('app.version')" var="appVersion" />
<spring:eval expression="T(java.time.Year).now().value" var="currentYear" />

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<!-- Footer -->
<footer class="main-footer">
    <strong>Copyright &copy; ${currentYear} jFeeSoft</strong>
    <span class="footer-rights">All rights reserved.</span>

    <div class="float-right d-sm-inline-block">
        <span id="app-meta">${appVersion}</span>
    </div>
</footer>

<style>
    @media (max-width: 576px) {
        .main-footer .footer-rights {
            display: none;
        }
    }

    @media (pointer: coarse) {
        .main-footer .footer-rights {
            display: none;
        }
    }
</style>

<script>
    (async () => {
        const el = document.getElementById('app-meta');
        if (!el) return;

        const isMobileTouch = () => window.matchMedia('(pointer: coarse)').matches;

        const shouldShowTime = () => {
            const finePointer = window.matchMedia('(pointer: fine)').matches;
            const wide = window.matchMedia('(min-width: 577px)').matches;
            return finePointer && wide;
        };

        const formatDeployedAt = (raw) => {
            if (!raw || raw === 'UNKNOWN') return 'UNKNOWN';

            if (shouldShowTime()) return String(raw);

            const s = String(raw);

            if (s.includes('T')) return s.split('T')[0];

            const spaceIndex = s.indexOf(' ');
            if (spaceIndex > 0) return s.substring(0, spaceIndex);

            return s;
        };

        let versionData = null;

        const updateDisplay = () => {
            if (!versionData) return;
            const v = versionData?.version ?? 'UNKNOWN';
            const d = formatDeployedAt(versionData?.deployedAt);
            el.innerHTML = `v` + v + ` ` + d;
        };

        window.addEventListener('resize', updateDisplay);

        try {
            const res = await fetch(`${frontPublicPath}/web/api/admin/version`, { cache: 'no-store' });
            if (!res.ok) throw new Error('HTTP ' + res.status);

            versionData = await res.json();
            updateDisplay();
        } catch {
        }
    })();
</script>