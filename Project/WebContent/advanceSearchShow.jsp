<%@ include file="common/header.jspf"%>

<%@ include file="common/navbar.jspf"%>
<div class="container">
	<div class="row">
		<div class="col-sm-12 col-md-10 col-md-offset-1">
			<div>
				<ul class="center">
					<li class="horizontal">Sort:</li>
					<li class="horizontal"><a
						href="advancesearch?sort=title
 	&title=${title}&year=${year}&director=${director}&${firstName}&${lastName}">By
							Title</a></li>
					<li class="horizontal"><a
						href="advancesearch?sort=year&&title=${title}
 	&year=${year}&director=${director}&${firstName}&${lastName}">By
							Year</a></li>
				</ul>
			</div>

			<%@ include file="common/showMovie.jspf"%>

			<div style="text-align: center">
				<nav aria-label="pagination">
					<ul class="pagination">
						<li><a
							href="advancesearch?paging=previous&req=advancesearch
			&title=${title}&year=${year}&director=${director}&${firstName}&${lastName}"
							aria-label="Previous"> <spanaria-hidden="true">&laquo;</span></a></li>
						<li class="active"><span>${page} <span class="sr-only">(current)</span></span>
						</li>
						<li><a
							href="advancesearch?paging=next&req=advancesearch
			&title=${title}&year=${year}&director=${director}&${firstName}&${lastName}"
							aria-label="Next"> <span aria-hidden="true">&raquo;</span></a></li>
					</ul>
				</nav>
			</div>

		</div>
	</div>
</div>
<%@ include file="common/footer.jspf"%>
