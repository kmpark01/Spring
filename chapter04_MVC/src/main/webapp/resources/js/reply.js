const replyService = (function(){
  // 댓글 삽입 함수
  function add(vo, callback){
    fetch('/replies/new', {
      method : 'post',
      body : JSON.stringify(vo),
      headers : {
        'Content-type' : 'application/json; charset=utf-8' 
      }
    })
      .then(response => response.text())
      .then(data => {
        callback(data);
      })
      .catch(err => console.log(err));
  }

  // 댓글 목록 함수
  function getList(bno, callback){
    fetch(`/replies/pages/${bno}.json`)
      .then(response => response.json())
      .then(data => {
        callback(data);
      })
      .catch(err => console.log(err));
  }
  
  // 댓글 삭제 함수
  function remove(rno, callback){
    fetch(`/replies/${rno}`,{
      method : 'delete',
      body : JSON.stringify(rno),
      headers : {
        'Content-type' : 'application/json; charset=utf-8'
      }
    })
      .then(response => response.text())
      .then(data => {
        callback(data);
      })
      .catch(err => console.log(err));
  }
  
  // 댓글 수정 함수
  function update(vo, callback){
    fetch(`/replies/${vo.rno}`, {
      method : 'put',
      body : JSON.stringify(vo),
      headers : {
        'Content-type' : 'application/json; charset=utf-8'
      }
    })
      .then(response => response.text())
      .then(data => {
        callback(data);
      })
      .catch(err => console.log(err));
  }
  // 댓글 조회 함수
  function get(rno, callback){
    fetch(`/replies/${rno}.json`)
      .then(response => response.json())
      .then(data => {
        callback(data);
      })
      .catch(err => console.log(err));
  }
  return{
    add : add,
    getList : getList,
    remove : remove,
    update : update,
    get : get
  }
})();