'use strict'

class KeyboardComponent {
    constructor(keyboardInputComponent) {
        this._render(keyboardInputComponent);
    }

    _render(keyboardInputComponent) {
        this._el = document.createElement('div');
        this._el.innerHTML = `<div class="container">
                                  <button type="button" class="btn btn-outline-success">1</button>
                                  <button type="button" class="btn btn-outline-success">2</button>
                                  <button type="button" class="btn btn-outline-success">3</button>
                                  <button type="button" class="btn btn-outline-success">4</button>
                                  <button type="button" class="btn btn-outline-success">5</button>
                                  </div>
                              <div class="container">
                                  <button type="button" class="btn btn-outline-success">6</button>
                                  <button type="button" class="btn btn-outline-success">7</button>
                                  <button type="button" class="btn btn-outline-success">8</button>
                                  <button type="button" class="btn btn-outline-success">9</button>
                                  <button type="button" class="btn btn-outline-success">0</button>
                              </div>`;

        this._addEventHandlersForButtons(keyboardInputComponent);
    }

    _addEventHandlersForButtons(keyboardInputComponent) {
        let buttons = this._el.querySelectorAll('button');
        for(let i = 0; i < buttons.length; i++) {
            buttons[i].onclick = function() {
                if(keyboardInputComponent.maxLength === -1 || keyboardInputComponent.value.length < keyboardInputComponent.maxLength) {
                    keyboardInputComponent.value += buttons[i].innerText;
                    keyboardInputComponent.dispatchEvent(new Event('textChanged'));
                }
            };
        }
    }

    get element() {
        return this._el;
    }
}