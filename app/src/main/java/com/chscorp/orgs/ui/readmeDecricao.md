## Sobre uso de coroutines na DescricaoActivity

 ```
val scope = CoroutineScope(Dispatchers.IO)
```
o código acima é usado pra definir em qual thread o o bloco de código vai ser executado.
`CoroutineScope` é usado selecionar é qual thread vai ser executado, `Dispatchers.IO` é o tipo de thread que vai acorrer a execução.

`Dispatchers.IO` indica que a execução vai acontecer em uma thread secundaria

`Dispatchers.Main` indica que a execução vai acontecer na thread principal, alterações visuais na tela da activity só acontecem na thread principal

 ```
scope.launch {
            produtoDesc = produtoDao.buscaPorId(produtoId)
            withContext(Dispatchers.Main) {
                produtoDesc?.let {
                    preencheCampos(it)
                } ?: finish()
            }
        }
```

`withContext` também serve para indicar qual thread vai ser usada pra executar o bloco, no caso acima é definido que o codigo vai rodar na thread principal porque a função `preencheCampos` faz alterações diretamente na parte visual da activity.

`launch` é usado pra rodar fuções suspensas 