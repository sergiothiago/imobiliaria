----- CIDADE E BAIRRO
INSERT INTO `cidade` (`codigo_cidade`, `nome_cidade`, `data_atualizacao_cidade`, `data_criacao_cidade`) VALUES (NULL, 'Visconde do Rio Branco', '2020-05-01 00:00:00', '2020-05-01 00:00:00');

INSERT INTO `cidade` (`codigo_cidade`, `nome_cidade`, `data_atualizacao_cidade`, `data_criacao_cidade`) VALUES (NULL, 'Ubá', '2020-05-01 00:00:00', '2020-05-01 00:00:00');

INSERT INTO `bairro` (`codigo_bairro`, `nome_bairro`, `codigo_cidade_bairro`, `data_atualizacao_bairro`, `data_criacao_bairro`) VALUES (NULL, 'São Jorge', '1', '2020-05-01 00:00:00', '2020-05-01 00:00:00');

INSERT INTO `bairro` (`codigo_bairro`, `nome_bairro`, `codigo_cidade_bairro`, `data_atualizacao_bairro`, `data_criacao_bairro`) VALUES (NULL, 'Primavera', '2', '2020-05-01 00:00:00', '2020-05-01 00:00:00');
----- ADMINISTRADOR E CLIENTE

INSERT INTO `pessoa` (`codigo_pessoa`, `nome_pessoa`, `email_pessoa`, `senha_pessoa`,
`perfil_pessoa`, `data_atualizacao_pessoa`, `data_criacao_pessoa`) 
VALUES (NULL, 'Sérgio Mendonça', 'admin@imobiliaria.com.br', 
'$2a$06$xIvBeNRfS65L1N17I7JzgefzxEuLAL0Xk0wFAgIkoNqu9WD6rmp4m', 'ROLE_ADMIN', 
'2020-05-01 00:00:00', '2020-05-01 00:00:00');

INSERT INTO `administrador` (`codigo_pessoa`) VALUES ('1');

INSERT INTO `pessoa` (`codigo_pessoa`, `nome_pessoa`, `email_pessoa`, `senha_pessoa`,
`perfil_pessoa`, `data_atualizacao_pessoa`, `data_criacao_pessoa`) 
VALUES (NULL, 'Marcos Bonifácio', 'bonifacio@gmail.com', '$2a$06$xIvBeNRfS65L1N17I7JzgefzxEuLAL0Xk0wFAgIkoNqu9WD6rmp4m',
'ROLE_CLIENTE', '2020-05-01 00:00:00', '2020-05-01 00:00:00');

INSERT INTO `cliente` (`codigo_pessoa`) VALUES ('2');
----- IMOVEL

INSERT INTO `imovel` (`codigo_imovel`, `referencia_imovel`, `titulo_imovel`, `corretor_imovel`,
`proprietario_imovel`, `descricao_imovel`, `preco_imovel`, `cep_imovel`, `numero_imovel`,
`rua_imovel`, `codigo_bairro_imovel`, `codigo_cidade_imovel`, `area_imovel`, `destaque_imovel`,
`status_imovel`, `especificacao_imovel`,`tipo_imovel`, `data_atualizacao_imovel`, `data_criacao_imovel`) VALUES (NULL, '102030', 'Casa Aromatica',
'Titinha corretora', 'João sem braço', 'Uma bela casa muito engraçada', '10000',
'36520000', '20', 'Alameda dos Anjos', 1, 1, '1000',
'0', 'ATIVO', 'VENDA' , 'CASA','2020-04-01 00:00:00', '2020-04-01 00:00:00');

INSERT INTO `casa` (`codigo_imovel`,
`quantidade_quartos_casa`, `quantidade_salas_casa`,
`quantidade_cozinhas_casa`, `quantidade_banheiros_casa`,
`quantidade_garagens_casa`) VALUES ('1', '1', '1', '1', '1', '1');

INSERT INTO `imovel` (`codigo_imovel`, `referencia_imovel`, `titulo_imovel`, `corretor_imovel`,
`proprietario_imovel`, `descricao_imovel`, `preco_imovel`, `cep_imovel`, `numero_imovel`,
`rua_imovel`, `codigo_bairro_imovel`, `codigo_cidade_imovel`, `area_imovel`, `destaque_imovel`,
`status_imovel`, `especificacao_imovel`, `tipo_imovel`,`data_atualizacao_imovel`, `data_criacao_imovel`)
VALUES (NULL, '2112', 'Apartamento bem localizado', 'Marcos Silva', 'João beneditino',
'Apartamento amplo bem localizado', '100000', '36500067', '20', 'Rua dos Anjos',
2, 2, '200', '0', 'ATIVO', 'VENDA', 'APARTAMENTO', '2020-04-01 00:00:00', '2020-04-01 00:00:00');

INSERT INTO `apartamento` (`codigo_imovel`, `quantidade_quartos_apartamento`,
`quantidade_salas_apartamento`, `quantidade_cozinhas_apartamento`, `quantidade_banheiros_apartamento`,
`quantidade_garagens_apartamento`, `preco_condominio_apartamento`)
VALUES ('2', '1', '1', '1', '1', '1', '100');

INSERT INTO `imovel` (`codigo_imovel`, `referencia_imovel`, `titulo_imovel`,
`corretor_imovel`, `proprietario_imovel`, `descricao_imovel`, `preco_imovel`,
`cep_imovel`, `numero_imovel`, `rua_imovel`, `codigo_bairro_imovel`, `codigo_cidade_imovel`,
`area_imovel`, `destaque_imovel`, `status_imovel`, `especificacao_imovel`, `tipo_imovel`, `data_atualizacao_imovel`, `data_criacao_imovel`)
VALUES (NULL, '9910', 'Galpão produção', 'Magnata Imoveis', 'João Magnata',
'Excelente galpão para produção', '20000', '36520000', '30', 'Alameda Trenti',
1, 1, '3000', '1', 'ATIVO','VENDA', 'GALPAO' , '2020-04-01 00:00:00', '2020-04-01 00:00:00');

INSERT INTO `galpao` (`codigo_imovel`) VALUES ('3');

INSERT INTO `favorito_imovel_cliente` (`codigo_imovel`, `codigo_pessoa`) VALUES ('1', '2'), ('2', '2');