<%@ include file="common/header.jspf"%>
<%@ include file="common/navbar.jspf"%>
<div class="container">
	<div class="row">
		<div class="col-sm-12 col-md-10 col-md-offset-1">
			<div class="media">
				<div class="media-left media-middle">
					<a href="#"> <img class="media-object" src="${star.photoUrl}"
						width="150" height="200" alt="No Image"></a>
				</div>
				<div class="media-body" class="center">
					<ul style="list-style-type:none">
						<li>ID: ${star.id}
						<li>First Name: ${star.firstName}</li>
						<li>Last Name: ${star.lastName}</li>
						<li>DOB: ${star.dob}</li>
						<li class="horizontal">Movies:</li>
						<c:forEach var="movie" items="${star.movies}">
							<li class="horizontal"><a href="singlemovie?id=${movie.id}">${movie.title}</a></li>
							<li class="horizontal">,</li>
						</c:forEach>

					</ul>
				</div>
			</div>
		</div>
	</div>
</div>


<%@ include file="common/footer.jspf"%>




