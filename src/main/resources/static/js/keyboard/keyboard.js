'use strict'

let loadKeyboard = function () {
    document.addEventListener("DOMContentLoaded", ready);

    function ready() {
        generateKeyboard();
    }

    function generateKeyboard() {
        let keyBoardGroupContainer = document.body.querySelector('.keyboard-group-container');// todo: handle situation with several keyboards on the page
        let keyboardInputComponent = keyBoardGroupContainer.querySelector('.atm-keyboard-input');
        addInputRestrictions(keyboardInputComponent);

        let keyboardHolder = keyBoardGroupContainer.querySelector('.atm-keyboard-component')
        keyboardHolder.appendChild((new KeyboardComponent(keyboardInputComponent)).element);
    }

    function addInputRestrictions(keyboardInputComponent) {
        keyboardInputComponent.readOnly=true;
    }
};

loadKeyboard();