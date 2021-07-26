# Criando um App de Cartão de Visitas em Kotlin

Aplicativo de lista de cartão de visita onde é possível manter os dados nome, empresa, telefone de contato, email e cor de fundo do cartão utilizando banco de dados Room.

Repositório baseado no código de Igor Bagliotti (https://github.com/igorbag/Business-Card), apresentado no projeto da DIO "Criando um App de Cartão de Visitas em Kotlin", realizado durante o bootcamp Inter Android Developer.


## <br />Tecnologias utilizadas 
1. Room
2. MVVM
3. ViewBinding
4. LiveData
5. ListAdapter
6. Material Design

## <br />Features adicionadas
1. Substituição de TextInputLayout por MaterialAutoCompleteTextView no TextInputLayout, para inserção de lista DropDown de cores;
2. Strings de entrada de cores exibidas e reconhecidas em português, mapeadas para suas contrapartes em inglês da classe Color, possibilitando uma personalização mais fácil dos cartões de visita;
3. Adição de tratamento de erros e/ou campos faltantes nas entradas de dados da Activity de adição de novos cartões, impedindo a adição de dados inválidos de cor, ou strings vazias, ao banco de dados interno usado.

## <br />Outras mudanças
1. Mudança de API mínima de 21 para 24;
2. Modificação do método de compartilhamento de imagem do cartão, adicionando FileProvider para gerar a URI da imagem, para possibilitar o compartilhamento de imagens nas versões 24+ a 29, o qual gerava a exceção android.os.FileUriExposedException originalmente.


