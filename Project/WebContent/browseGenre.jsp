<%@ include file="common/header.jspf"%>

<%@ include file="common/navbar.jspf"%>
<div>
 <ul class="center">
 	<li class="horizontal">Sort: </li>
 	<li class="horizontal"><a href="browsegenre?sort=title&genre=${genre}">By Title</a></li>
 	<li class="horizontal"><a href="browsegenre?sort=year&genre=${genre}">By Year</a></li>
 </ul>
</div>

<div>
 <ul class="center">
 	<li class="horizontal">Number of displayed items: </li>
 	<li class="horizontal"><a href="browsegenre?limit=10&genre=${genre}">10</a></li>
 	<li class="horizontal"><a href="browsegenre?limit=25&genre=${genre}">25</a></li>
 	<li class="horizontal"><a href="browsegenre?limit=50&genre=${genre}">50</a></li>
 	<li class="horizontal"><a href="browsegenre?limit=100&genre=${genre}">100</a></li>
 </ul>
</div>

<%@ include file="common/showMovie.jspf"%>

<div style="text-align: center">
	<nav aria-label="pagination">
		<ul class="pagination">
			<li><a href="browsegenre?paging=previous&req=genre&genre=${genre}" aria-label="Previous"> <spanaria-hidden="true">&laquo;</span></a></li>
			<li class="active"><span>${page} <span class="sr-only">(current)</span></span>
			</li>
			<li><a href="browsegenre?paging=next&req=genre&genre=${genre}" aria-label="Next"> <span
					aria-hidden="true">&raquo;</span></a></li>
		</ul>
	</nav>
</div>


<%@ include file="common/footer.jspf"%>
