document.querySelectorAll('.menu a').forEach(a => {
  a.addEventListener('click', e => {
    e.preventDefault();

    let menu = e.target.getAttribute('href');

    if(menu === 'boardList'){
      location.href = '/board/list';
    }
  });
});