<%@ include file="common/header.jspf"%>
<%@ include file="common/navbar.jspf"%>
<div>
 <ul class="center">
 	<li class="horizontal">Sort: </li>
 	<li class="horizontal"><a href="browsetitle?sort=title&character=${character}">By Title</a></li>
 	<li class="horizontal"><a href="browsetitle?sort=year&character=${character}">By Year</a></li>
 </ul>
</div>

<div>
 <ul class="center">
 	<li class="horizontal">Number of displayed items: </li>
 	<li class="horizontal"><a href="browsetitle?limit=10&character=${character}">10</a></li>
 	<li class="horizontal"><a href="browsetitle?limit=25&character=${character}">25</a></li>
 	<li class="horizontal"><a href="browsetitle?limit=50&character=${character}">50</a></li>
 	<li class="horizontal"><a href="browsetitle?limit=100&character=${character}">100</a></li>
 </ul>
</div>

<%@ include file="common/showMovie.jspf"%>

<div style="text-align: center">
	<nav aria-label="pagination">
		<ul class="pagination">
			<li><a href="browsetitle?paging=previous&req=title&character=${character}" aria-label="Previous"> <spanaria-hidden="true">&laquo;</span></a></li>
			<li class="active"><span>${page} <span class="sr-only">(current)</span></span>
			</li>
			<li><a href="browsetitle?paging=next&req=title&character=${character}" aria-label="Next"> <span
					aria-hidden="true">&raquo;</span></a></li>
		</ul>
	</nav>
</div>


<%@ include file="common/footer.jspf"%>
