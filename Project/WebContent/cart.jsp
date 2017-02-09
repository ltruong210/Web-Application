<%@ include file="common/header.jspf"%>
<%@ include file="common/navbar.jspf"%>



<div class="container">
	<div class="row">
		<div class="col-sm-12 col-md-10 col-md-offset-1">
			<p>
				<font size="10px"><strong>${emptyCart}</strong></font>
			</p>
			<form action="cart?update="true" method="post">

				<table class="table table-hover">
					<thead>
						<tr>
							<th>Product</th>
							<th>Quantity</th>
							<th class="text-center">Price</th>
							<th class="text-center">Subtotal</th>
							<th> </th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="item" items="${items}">
							<tr>
								<td class="col-sm-8 col-md-6">
									<div class="media">
										<div class="media-body">
											<a href="singlemovie?id=${item.getMovie().getId()}">${item.getMovie().getTitle()}</a>
										</div>
									</div>
								</td>
								<td class="col-sm-1 col-md-1" style="text-align: center"><input
									type="number" name="${item.getMovie().getId()}" class="form-control" value="${item.getQuantity()}">
								</td>

								<td class="col-sm-1 col-md-1 text-center"><strong>$5</strong></td>
								<td class="col-sm-1 col-md-1 text-center"><strong>$${item.getQuantity()*5}</strong></td>
								<td class="col-sm-1 col-md-1"><a class="btn btn-danger"
									href="cart?remove=${item.getMovie().getId()}"> <span
										class="glyphicon glyphicon-remove"></span>Remove
								</a>
							</tr>
						</c:forEach>
						<tr>
							<td> </td>
							<td> </td>
							<td> </td>
							<td><h3>Total</h3></td>
							<td class="text-right"><h3>
									<strong>$${total}</strong>
								</h3></td>
						</tr>
						<tr>
							<td> </td>
							
							<td><a class="btn btn-default" href="home"> <span
									class="glyphicon glyphicon-shopping-cart"></span> Continue
									Shopping
							</a></td>
							<td>
								<button type="submit" class="btn btn-success">
									<span class="glyphicon glyphicon-ok"></span>Update Cart
								</button>
							</td>
							<td><a class="btn btn-danger" href="cart?removeAll=true">
									<span class="glyphicon glyphicon-remove"></span>Remove All
							</a></td>
							<td><a class="btn btn-success" href="checkout.jsp">
									Checkout <span class="glyphicon glyphicon-play">
							</a></td>
							
						</tr>


					</tbody>
				</table>
			</form>
		</div>
	</div>
</div>
<%@ include file="common/footer.jspf"%>

