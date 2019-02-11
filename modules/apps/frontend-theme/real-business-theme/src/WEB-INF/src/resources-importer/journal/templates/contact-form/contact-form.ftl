<div class="contact-wrapper">
			<h2>${ContactHeader.getData()}</h2>

			<form action="" id="contact-form" method="" role="form">

				<div class="controls">

					<div class="row">
						<div class="col-md-6">
							<div class="form-group">
								<label for="form_name"><@liferay.language key="name" /> (required)</label>
								<input id="form_name" type="text" name="name" class="form-control"
								required="required" data-error="Firstname is required.">

								<div class="help-block with-errors"></div>
							</div>
						</div>

						<div class="col-md-6">
							<div class="form-group">
								<label for="form_email"><@liferay.language key="email" /> (required)</label>
								<input id="form_email" type="email" name="email" class="form-control"
								required="required" data-error="Valid email is required.">

								<div class="help-block with-errors"></div>
							</div>
						</div>

					</div>

					<div class="row">
						<div class="col-md-12">
							<div class="form-group">
								<label for="form_email"><@liferay.language key="email-subject" /></label>
								<input id="form_subject" type="text" name="text" class="form-control"
								required="required" data-error="Valid subject is required.">

								<div class="help-block with-errors"></div>
							</div>
						</div>
					</div>

					<div class="row">
						<div class="col-md-12">
							<div class="form-group">
								<label for="form_message"><@liferay.language key="message" /></label>
								<textarea id="form_message" name="message" class="form-control" rows="12" required="required"
								data-error="Please, leave us a message."></textarea>

								<div class="help-block with-errors"></div>
							</div>
						</div>

						<div class="col-md-12">
							<input class="btn btn-send" type="submit" value="Send">
						</div>

					</div>
				</div>
			</form>
		</div>