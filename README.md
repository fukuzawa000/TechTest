■環境説明</br>
　・バックエンド：Java（Springboot 2.6.4）、JDK8</br>
　・DB：Mysql 5.7</br>
</br>
■機能説明</br>
　該当APIが下記の機能を提供しています。</br>
　・認証</br>
　　POST　/auth</br>
　・ログアウト</br>
　　DELETE　/logout</br>
　・映画一覧取得</br>
　　GET /movies?search={search}</br>
　　※パラメータsearchは省略可能です。</br>
　・指定映画の情報の取得</br>
　　GET /movies/:id</br>
　・気に入れ一覧取得</br>
　　GET /favorites</br>
　・映画を気に入れに追加</br>
　　POST /favorites/:id</br>
　・気に入れから削除済</br>
　　DELETE　/favorites/:id</br>
</br>
■利用手順</br>
　①ユーザ認証</br>
　　useridとpasswordをPostでサブミットする。</br>
　　※既に登録しているユーザ情報は下記です。</br>
　　　ユーザID：user1</br>
　　　パスワード：passwd1</br>
　②トークン埋め込み</br>
　　①で取得したトークンとユーザIDを次回リクエストヘッダーに埋め込み</br>
　　　項目ID：userid、token</br>
　③認証以外のAPIは利用可能となる</br>
　　②実施後、機能説明で記載している機能は利用可能となる。</br>
</br>
■テスト環境</br>
　・下記のURLを公開しているので、テストは可能です。</br>
　　URL：https://wwws.3t-soft.co.jp/Ttest</br>
　　例：</br>
　　　→ユーザ認証</br>
　　　　POST　https://wwws.3t-soft.co.jp/Ttest/auth</br>
　　　→映画一覧</br>
　　　　GET　https://wwws.3t-soft.co.jp/Ttest/movies</br>
　　　→映画検索</br>
　　　　GET　https://wwws.3t-soft.co.jp/Ttest/movies?search={search}</br>
</br>
※補足説明</br>
　開発時間が不足なので、下記の機能は未実装となります。</br>
　現：現在実装した状態</br>
　実：実際な開発中にやるべきもの</br>
　■セキュリティ</br>
　　・セキュリティ全体</br>
　　　→現：なし</br>
　　　→実：Spring Securityを使うべき</br>
　　・クロスドメインの公開範囲</br>
　　　→現：全ドメイン公開</br>
　　　→実：特定なサーバにに対して公開</br>
　　・IPアドレス制限</br>
　　　→現：なし</br>
　　　→実：特定なサーバのIPからのアクセスのみ利用可能</br>
　　・パスワード</br>
　　　→現：そのままDBに保存</br>
　　　→実：MD５暗号化後、DBに保存。</br>
</br>
　■機能省略化</br>
　　・トークン発行</br>
　　　→現：特定な文字列を暗号化した後、トークンを扱う</br>
　　　→実：JWTを使うべき</br>
　　・トークン利用期限</br>
　　　→現：DBにトークンテーブルを作成し、トークン期限切れを実現している</br>
　　　→実：JWTを使うべき</br>
　　・トークン有効期限</br>
　　　→現：テストしやすいために、１時間としている</br>
　　　→実：８時間〜２４時間のほうが多いい。</br>
　　・パラメータの入力チェック</br>
　　　→現：必須チェックのみ</br>
　　　→実：必須、長さ、英数字などのチェックが必要</br>
　　・二重サブミット</br>
　　　→現：制限なし</br>
　　　→実：二重サブミット制限あり</br>
</br>
　詳細な仕様は分からなくて、一部想定で実装しています。</br>
　■ページング機能</br>
　　１０００件まで映画検索をでき、フロントエンド側でページング機能を実現する。</br>




