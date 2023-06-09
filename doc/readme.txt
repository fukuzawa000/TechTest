■環境説明
　・バックエンド：Java（Springboot 2.6.4）、JDK8
　・DB：Mysql 5.7

■機能説明
　該当APIが下記の機能を提供しています。
　・認証
　　POST　/auth
　・ログアウト
　　DELETE　/logout
　・映画一覧取得
　　GET /movies?search={search}
　　※パラメータsearchは省略可能です。
　・指定映画の情報の取得
　　GET /movies/:id
　・気に入れ一覧取得
　　GET /favorites
　・映画を気に入れに追加
　　POST /favorites/:id
　・気に入れから削除済
　　DELETE　/favorites/:id

■利用手順
　①ユーザ認証
　　useridとpasswordをPostでサブミットする。
　　※既に登録しているユーザ情報は下記です。
　　　ユーザID：user1
　　　パスワード：passwd1
　②トークン埋め込み
　　①で取得したトークンとユーザIDを次回リクエストヘッダーに埋め込み
　　　項目ID：userid、token
　③認証以外のAPIは利用可能となる
　　②実施後、機能説明で記載している機能は利用可能となる。

■テスト環境
　・下記のURLを公開しているので、テストは可能です。
　　URL：https://wwws.3t-soft.co.jp/Ttest
　　例：
　　　→ユーザ認証
　　　　POST　https://wwws.3t-soft.co.jp/Ttest/auth
　　　→映画一覧
　　　　GET　https://wwws.3t-soft.co.jp/Ttest/movies
　　　→映画検索
　　　　GET　https://wwws.3t-soft.co.jp/Ttest/movies?search={search}

※補足説明
　開発時間が不足なので、下記の機能は未実装となります。
　現：現在実装した状態
　実：実際な開発中にやるべきもの
　■セキュリティ
　　・セキュリティ全体
　　　→現：なし
　　　→実：Spring Securityを使うべき
　　・クロスドメインの公開範囲
　　　→現：全ドメイン公開
　　　→実：特定なサーバにに対して公開
　　・IPアドレス制限
　　　→現：なし
　　　→実：特定なサーバのIPからのアクセスのみ利用可能
　　・パスワード
　　　→現：そのままDBに保存
　　　→実：MD５暗号化後、DBに保存。

　■機能省略化
　　・トークン発行
　　　→現：特定な文字列を暗号化した後、トークンを扱う
　　　→実：JWTを使うべき
　　・トークン利用期限
　　　→現：DBにトークンテーブルを作成し、トークン期限切れを実現している
　　　→実：JWTを使うべき
　　・トークン有効期限
　　　→現：テストしやすいために、１時間としている
　　　→実：８時間〜２４時間のほうが多いい。
　　・パラメータの入力チェック
　　　→現：必須チェックのみ
　　　→実：必須、長さ、英数字などのチェックが必要
　　・二重サブミット
　　　→現：制限なし
　　　→実：二重サブミット制限あり

　詳細な仕様は分からなくて、一部想定で実装しています。
　■ページング機能
　　１０００件まで映画検索をでき、フロントエンド側でページング機能を実現する。
  
　■ソースコード
　　ご提示頂いたレジストリへのコミット権限がなさそうなので、ソースコードを下記のレジストリにコミットしました。
　　ここでソースレビューをいただければと思います。
　　GithubURL：https://github.com/fukuzawa000/TechTest
　　Gitレジストリ：https://github.com/fukuzawa000/TechTest.git




