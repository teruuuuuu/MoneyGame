# MoneyGame
チキンゲームをベースにしたお金の取り合いゲームです。    
2人のプレイヤーは初期に所持金1万円を与えられ、ターン毎で初期の所持金からお金を出すようにし相手よりも少ない金額を出した方が相手の出した金額をもらえます。
プレイヤーは10ターンを争い10ターンで出す金額の合計は1万円にならなければいけないというルールがあります。

## 遊び方
### windows環境の場合
```
gradlew.bat build
java -jar build/libs/MonyGame.jar
```

### Macの場合
```
./gradlew build
java -jar build/libs/MonyGame.jar
```