<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
    <script src="/js/keyboard-component.js"></script>
    <script src="/js/keyboard.js"></script>
    <script src="/js/atm-components-initializer.js"></script>

</head>
<body>
    <div class="container atm-keyboard-group-container mt-4">
        <div class="container">
          <form action="/card/number" method="POST" class="atm-auth-form">
            <div class="form-group row">
              <label for="atmCardNumber" class="col-form-label">Card Number</label>
              <div class="col-sm-4">
                <div class="input-group">
                  <input class="form-control atm-keyboard-input atm-card-number" type="text"
                         placeholder="1111-1111-1111-1111" maxlength='19'
                         name="atmCardNumber"
                         id="atmCardNumber">
                  <span class="input-group-btn">
                    <button class="btn btn-secondary clear-atm-input" type="button" for="atmCardNumber">Clear</button>
                  </span>
                </div>
              </div>
              <button type="submit" class="btn btn-success atm-send-btn">Send</button>
            </div>
          </form>
        </div>

        <div class="container col-sm-9 atm-keyboard-component"></div>
    </div>
</body>
</html>

