'use strict'

let loadKeyboard = function () {
    document.addEventListener("DOMContentLoaded", ready);

    function ready() {
        generateKeyboard();
    }

    function generateKeyboard() {
        let keyBoardGroupContainer = document.body.querySelector('.atm-keyboard-group-container');// todo: handle situation with several keyboards on the page
        let keyboardInputComponent = keyBoardGroupContainer.querySelector('.atm-keyboard-input');

        let keyboardHolder = keyBoardGroupContainer.querySelector('.atm-keyboard-component')
        keyboardHolder.appendChild((new KeyboardComponent(keyboardInputComponent)).element);
    }
};

loadKeyboard();