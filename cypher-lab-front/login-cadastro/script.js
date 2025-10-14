document.addEventListener('DOMContentLoaded', () => {
    const eyeIcons = document.querySelectorAll('.eye-icon');

    eyeIcons.forEach(icon => {
        
        icon.addEventListener('click', () => {
            
            const passwordInput = icon.parentElement.querySelector('input');

            if (passwordInput.type === 'password') {
                passwordInput.type = 'text';
                icon.classList.replace('bx-hide', 'bx-show');
            } else {
                passwordInput.type = 'password';
                icon.classList.replace('bx-show', 'bx-hide');
            }
        });
    });
});