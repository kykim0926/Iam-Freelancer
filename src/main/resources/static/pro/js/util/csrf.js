// csrf 값 설정
//<html lang="ko" xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org">
//<meta name="_csrf" th:content="${_csrf.token}"/>
//<meta name="_csrf_header" th:content="${_csrf.headerName}"/>
//<script src="/pro/js/util/csrf.js"></script>
var CSRF_TOKEN = $("meta[name='_csrf']").attr("content");
var CSRF_HEADER = $("meta[name='_csrf_header']").attr("content");