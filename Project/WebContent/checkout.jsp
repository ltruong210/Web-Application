<%@ include file="common/header.jspf"%>
<%@ include file="common/navbar.jspf"%>

<div class="container">
	<div class="row">
		<div class="col-sm-12 col-md-10 col-md-offset-1">
			<form action="checkout" method="post">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title center">
							<strong>Check Out</strong>
						</h3>
					</div>
					<div class="panel-body ">
						<div class="input-group" style="padding: 10px">
							<span class="input-group-addon" id="basic-addon1">First
								Name: </span> <input type="text" name="firstName" class="form-control" placeholder=""
								aria-describedby="basic-addon1">
						</div>
						
						<div class="checkout input-group" style="padding: 10px">
							<span class="input-group-addon" id="basic-addon1">Last
								Name: </span> <input type="text" name="lastName" class="form-control" placeholder=""
								aria-describedby="basic-addon1">
						</div>
						
						<div class="input-group" style="padding: 10px">
							<span class="input-group-addon" id="basic-addon1">Credit
								Card ID: </span> <input type="text" name="ccid" class="form-control" placeholder=""
								aria-describedby="basic-addon1">
						</div>
						
						<div class="input-group " style="padding: 10px">
							<span class="input-group-addon" id="basic-addon1">Expiration
								Date: </span> <input type="text" name="exp" class="form-control" placeholder=""
								aria-describedby="basic-addon1">
						</div>
						
						<div>
							<h3>Total: $${total}</h3>
						</div>
						
						<div class="btn-group text-center" role="group" style="padding = 10px">
							<button type="submit" class="btn btn-success">Place order</button>
						</div>
						
						
					</div>
				</div>

			</form>
		</div>
	</div>
</div>
<%@ include file="common/footer.jspf"%>

