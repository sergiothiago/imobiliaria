INSERT INTO `empresa` (`id`, `cnpj`, `data_atualizacao`, `data_criacao`, `razao_social`) 
VALUES (NULL, '82198127000121', CURRENT_DATE(), CURRENT_DATE(), 'Kazale IT');

INSERT INTO `funcionario` (`id`, `cpf`, `data_atualizacao`, `data_criacao`, `email`, `nome`, 
`perfil`, `qtd_horas_almoco`, `qtd_horas_trabalho_dia`, `senha`, `valor_hora`, `empresa_id`) 
VALUES (NULL, '16248890935', CURRENT_DATE(), CURRENT_DATE(), 'admin@kazale.com', 'ADMIN', 'ROLE_ADMIN', NULL, NULL, 
'$2a$06$xIvBeNRfS65L1N17I7JzgefzxEuLAL0Xk0wFAgIkoNqu9WD6rmp4m', NULL, 
(SELECT `id` FROM `empresa` WHERE `cnpj` = '82198127000121'));

INSERT INTO `imovel` (`codigo_imovel`, `referencia_imovel`, `titulo_imovel`, `corretor_imovel`,
`proprietario_imovel`, `descricao_imovel`, `preco_imovel`, `cep_imovel`, `numero_imovel`,
`rua_imovel`, `bairro_imovel`, `cidade_imovel`, `area_imovel`, `destaque_imovel`,
`status_imovel`, `especificacao_imovel`,`tipo_imovel`, `data_atualizacao_imovel`, `data_criacao_imovel`) VALUES (NULL, '102030', 'Casa Aromatica',
'Titinha corretora', 'João sem braço', 'Uma bela casa muito engraçada', '10000',
'36520000', '20', 'Alameda dos Anjos', 'Centro', 'Visc. do Rio Branco', '1000',
'0', 'ATIVO', 'VENDA' , 'CASA','2020-04-01 00:00:00', '2020-04-01 00:00:00');

INSERT INTO `casa` (`codigo_imovel`,
`quantidade_quartos_casa`, `quantidade_salas_casa`,
`quantidade_cozinhas_casa`, `quantidade_banheiros_casa`,
`quantidade_garagens_casa`) VALUES ('1', '1', '1', '1', '1', '1');

INSERT INTO `imovel` (`codigo_imovel`, `referencia_imovel`, `titulo_imovel`, `corretor_imovel`,
`proprietario_imovel`, `descricao_imovel`, `preco_imovel`, `cep_imovel`, `numero_imovel`,
`rua_imovel`, `bairro_imovel`, `cidade_imovel`, `area_imovel`, `destaque_imovel`,
`status_imovel`, `especificacao_imovel`, `tipo_imovel`,`data_atualizacao_imovel`, `data_criacao_imovel`)
VALUES (NULL, '2112', 'Apartamento bem localizado', 'Marcos Silva', 'João beneditino',
'Apartamento amplo bem localizado', '100000', '26500067', '20', 'Rua dos Anjos',
'Centro', 'Ubá', '200', '0', 'ATIVO', 'VENDA', 'APARTAMENTO', '2020-04-01 00:00:00', '2020-04-01 00:00:00');

INSERT INTO `apartamento` (`codigo_imovel`, `quantidade_quartos_apartamento`,
`quantidade_salas_apartamento`, `quantidade_cozinhas_apartamento`, `quantidade_banheiros_apartamento`,
`quantidade_garagens_apartamento`, `preco_condominio_apartamento`)
VALUES ('2', '1', '1', '1', '1', '1', '100');

INSERT INTO `imovel` (`codigo_imovel`, `referencia_imovel`, `titulo_imovel`,
`corretor_imovel`, `proprietario_imovel`, `descricao_imovel`, `preco_imovel`,
`cep_imovel`, `numero_imovel`, `rua_imovel`, `bairro_imovel`, `cidade_imovel`,
`area_imovel`, `destaque_imovel`, `status_imovel`, `especificacao_imovel`, `tipo_imovel`, `data_atualizacao_imovel`, `data_criacao_imovel`)
VALUES (NULL, '9910', 'Galpão produção', 'Magnata Imoveis', 'João Magnata',
'Excelente galpão para produção', '20000', '26520000', '30', 'Alameda Trenti',
'Centro', 'Visc. Rio Branco', '3000', '1', 'ATIVO','VENDA', 'GALPAO' , '2020-04-01 00:00:00', '2020-04-01 00:00:00');

INSERT INTO `galpao` (`codigo_imovel`) VALUES ('3');

INSERT INTO `cliente` (`codigo_cliente`, `nome_cliente`,
`email_cliente`, `senha_cliente`, `perfil_cliente`,
`data_atualizacao_cliente`, `data_criacao_cliente`)
VALUES (NULL, 'João', 'joao@gmail.com', '123', 'ROLE_CLIENTE',
'2020-04-01 00:00:00', '2020-04-01 00:00:00');

INSERT INTO `cliente` (`codigo_cliente`, `nome_cliente`,
`email_cliente`, `senha_cliente`, `perfil_cliente`,
`data_atualizacao_cliente`, `data_criacao_cliente`)
VALUES (NULL, 'Cassio', 'cassio@gmail.com', '123', 'ROLE_CLIENTE',
'2020-04-01 00:00:00', '2020-04-01 00:00:00');

INSERT INTO `administrador` (`codigo_administrador`, `nome_administrador`,
`email_administrador`, `senha_administrador`, `perfil_administrador`,
`data_atualizacao_administrador`, `data_criacao_administrador`)
VALUES (NULL, 'sergio takashi', 'sergiothiagovrb@gmail.com',
'123', 'ROLE_ADMIN', '2020-04-01 00:00:00', '2020-04-01 00:00:00');


INSERT INTO `favorito_imovel_cliente` (`codigo_imovel`, `codigo_cliente`) VALUES ('1', '1'), ('2', '1');