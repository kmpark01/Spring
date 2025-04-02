console.log('reply module....');

const replyService = (function(){
  // 댓글 삽입 함수
  function add(vo, callback){
    fetch('/reply/new', {
      method : 'post',
      body : JSON.stringify(vo),
      headers : {
        'Content-type' : 'application/json; charset=utf-8' 
      }
    })
      .then(response => response.text())
      .then(data => {
        console.log(data);
      })
      .catch(err => console.log(err));
  }

  // 댓글 목록 함수
  function getList(bno, callback){
    fetch(`/replies/pages/${bno}.json`)
      .then(response => response.json())
      .then(data => {
        console.log(data);
        console.log(`${bno}`);
      })
      .catch(err => console.log(err));
  }
  return{
    add : add,
    getList : getList
  }
})();