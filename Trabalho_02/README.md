Considere o cenário abaixo :
Uma companhia é organizada em departamentos. Cada departamento tem um nome e um
número. Além disso, um departamento controla vários projetos, cada um dos quais com um
nome, um número de identificação e o período de tempo no qual deve ser desenvolvido. Na
referida companhia, cada projeto somente pode ser desenvolvido por um departamento
específico.
Existem somente três tipos de funcionários que trabalham na companhia: pesquisador,
secretário e funcionário de limpeza. Para os pesquisadores, deseja-se armazenar: o nome, o
endereço, o sexo, a data de aniversário, o salário e a área de atuação. Para os secretários
deseja-se armazenar: o nome, o endereço, o sexo, a data de aniversário, o salário e o grau de
escolaridade. Já para os funcionários de limpeza, deseja-se armazenar: o nome, o endereço,
o sexo, a data de aniversário, o salário, o cargo e a jornada de trabalho. Os cargos dos
funcionários responsáveis pela limpeza são hierárquicos. Assim, deseja-se armazenar
também, para cada funcionário de limpeza, informações sobre o funcionário de limpeza que
o gerencia. Os funcionários da companhia são identificados por meio de um código de
identificação, e podem estar associados a apenas um único departamento.
Funcionários que são pesquisadores podem trabalhar em diversos projetos,
independentemente de esses projetos estarem sendo desenvolvidos no mesmo departamento
no qual o empregado está associado. Deve-se armazenar o número de horas semanais
trabalhadas por cada pesquisador em cada projeto no qual ele trabalha. Devem-se armazenar
também informações sobre os dependentes de cada funcionário para propósito de ajuda
familiar. Deve-se armazenar o nome, o sexo e a data de aniversário, além do grau de
parentesco com o funcionário. Sabendo que um funcionário pode ter vários dependentes.
O que deve ser feito:
1. Proponha um diagrama de classe e utilize o Mapeamento Objeto Relacional para
implementar o sistema proposto. O diagrama deve estar em um PDF ou em uma imagem
(formato .jpg ou .png).
2. Implemente o sistema proposto. Utilize o padrão DAO, conforme visto em sala. A
persistência dos dados do sistema deve ser feita utilizando o modelo JPA (utilize Hibernate,
que é uma implementação do modelo JPA). Você deve permitir a inserção, remoção e
consultas aos objetos por meio do seu sistema. Não é necessário que o sistema tenha
interface gráfica (a interação com o usuário pode ser feita via terminal). Essa parte do
trabalho deve ser apresentada até quarta-feira, 30/10/2019.
Obs.: O trabalho deve ser preferencialmente feito em duplas, mas pode ser feito
individualmente. Não é permitido uma equipe com mais de dois integrantes.
