# RestfulAsyncTask

HTTP GET/POST/DELETE/PUT을 위한 Reusable Asynchrous Task 입니다.
각 Activity에서 AsyncEventHandler를 implementation한 후, override된 메서드에서 이벤트 완료 시 작업할 내용을 구현하면 됩니다.

생성자 생성 시 TargetURL, MessageId, AsyncEventHandler, HeaderParams, Payload (URL Params) 를 넘겨서 생성한 후,

execute 호출하면 실행 됩니다.
