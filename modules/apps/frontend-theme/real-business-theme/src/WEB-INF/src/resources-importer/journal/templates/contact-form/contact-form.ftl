<div class="contact-wrapper">
			<h2>${ContactHeader.getData()}</h2>

			<form id="contact-form" method="" action="" role="form">
			
				<div class="controls">
			
					<div class="row">
						<div class="col-md-6">
							<div class="form-group">
								<label for="form_name">Your Name (required)</label>
								<input id="form_name" type="text" name="name" class="form-control"
								 required="required" data-error="Firstname is required.">
								<div class="help-block with-errors"></div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label for="form_email">Your Email (required)</label>
								<input id="form_email" type="email" name="email" class="form-control"
								required="required" data-error="Valid email is required.">
								<div class="help-block with-errors"></div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12">
							<div class="form-group">
								<label for="form_email">Subject</label>
								<input id="form_subject" type="text" name="text" class="form-control"
								 required="required" data-error="Valid subject is required.">
								<div class="help-block with-errors"></div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12">
							<div class="form-group">
								<label for="form_message">Your Message</label>
								<textarea id="form_message" name="message" class="form-control" rows="12" required="required"
								 data-error="Please, leave us a message."></textarea>
								<div class="help-block with-errors"></div>
							</div>
						</div>
						<div class="col-md-12">
							<input type="submit" class="btn btn-send" value="Send">
						</div>
					</div>
				</div>
			
			</form>
		</div>