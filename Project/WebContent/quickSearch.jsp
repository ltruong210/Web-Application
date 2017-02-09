<%@ include file="common/header.jspf"%>

<%@ include file="common/navbar.jspf"%>
<div>
 <ul class="center">
 	<li class="horizontal">Sort: </li>
 	<li class="horizontal"><a href="quicksearch?sort=title&pattern=${pattern}">By Title</a></li>
 	<li class="horizontal"><a href="quicksearch?sort=year&pattern=${pattern}">By Year</a></li>
 </ul>
</div>

<%@ include file="common/showMovie.jspf"%>

<div style="text-align: center">
	<nav aria-label="pagination">
		<ul class="pagination">
			<li><a href="quicksearch?paging=previous&req=quicksearch&pattern=${pattern}" aria-label="Previous"> <spanaria-hidden="true">&laquo;</span></a></li>
			<li class="active"><span>${page} <span class="sr-only">(current)</span></span>
			</li>
			<li><a href="quicksearch?paging=next&req=quicksearch&pattern=${pattern}" aria-label="Next"> <span
					aria-hidden="true">&raquo;</span></a></li>
		</ul>
	</nav>
</div>


<%@ include file="common/footer.jspf"%>
