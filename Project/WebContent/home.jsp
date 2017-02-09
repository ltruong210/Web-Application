<%@ include file="common/header.jspf"%>

<%@ include file="common/navbar.jspf"%>
<div>
 <ul class="center">
 	<li class="horizontal">Sort: </li>
 	<li class="horizontal"><a href="home?sort=title">By Title</a></li>
 	<li class="horizontal"><a href="home?sort=year">By Year</a></li>
 </ul>
</div>

<div>
 <ul class="center">
 	<li class="horizontal">Number of displayed items: </li>
 	<li class="horizontal"><a href="home?limit=10">10</a></li>
 	<li class="horizontal"><a href="home?limit=25">25</a></li>
 	<li class="horizontal"><a href="home?limit=50">50</a></li>
 	<li class="horizontal"><a href="home?limit=100">100</a></li>
 </ul>
</div>

<%@ include file="common/showMovie.jspf"%>

<div style="text-align: center">
	<nav aria-label="pagination">
		<ul class="pagination">
			<li><a href="home?paging=previous&req=home" aria-label="Previous"> <spanaria-hidden="true">&laquo;</span></a></li>
			<li class="active"><span>${page} <span class="sr-only">(current)</span></span>
			</li>
			<li><a href="home?paging=next&req=home" aria-label="Next"> <span
					aria-hidden="true">&raquo;</span></a></li>
		</ul>
	</nav>
</div>


<%@ include file="common/footer.jspf"%>
