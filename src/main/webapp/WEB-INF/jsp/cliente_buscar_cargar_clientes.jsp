<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>  
<%@ taglib prefix="c" uri='http://java.sun.com/jstl/core_rt'%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page isELIgnored="false"%>

var availableTags = [
<c:forEach items="${clientes}" var="e" varStatus="cont">
	"${e}",
</c:forEach>
"Último"
];

$(function() {

//	var availableTags = [ "ActionScript", "AppleScript", "Asp", "BASIC", "C",
//			"C++", "Clojure", "COBOL", "ColdFusion", "Erlang", "Fortran",
//			"Groovy", "Haskell", "Java", "JavaScript", "Lisp", "Perl", "PHP",
//			"Python", "Ruby", "Scala", "Scheme" ];

	$(".autocomplete").autocomplete({
		source : availableTags
	});
});