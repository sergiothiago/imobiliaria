CREATE TABLE `pessoa` (
  `codigo_pessoa` bigint(20) NOT NULL,
  `nome_pessoa` varchar(255) NOT NULL,
  `email_pessoa` varchar(255) NOT NULL,
  `senha_pessoa` varchar(255) NOT NULL,
  `perfil_pessoa` varchar(255) NOT NULL,
  `data_atualizacao_pessoa` datetime NOT NULL,
  `data_criacao_pessoa` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `administrador` (
  `codigo_pessoa` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `cliente` (
  `codigo_pessoa` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `cidade` (
  `codigo_cidade` bigint(20) NOT NULL,
  `nome_cidade` varchar(255) NOT NULL,
  `data_atualizacao_cidade` datetime NOT NULL,
  `data_criacao_cidade` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `bairro` (
  `codigo_bairro` bigint(20) NOT NULL,
  `nome_bairro` varchar(255) NOT NULL,
  `codigo_cidade_bairro` bigint(20) NOT NULL,
  `data_atualizacao_bairro` datetime NOT NULL,
  `data_criacao_bairro` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `imovel` (
  `codigo_imovel` bigint(20) NOT NULL,
  `referencia_imovel` varchar(255) NOT NULL,
  `titulo_imovel` varchar(255) NOT NULL,
  `corretor_imovel` varchar(255) ,
  `proprietario_imovel` varchar(255) ,
  `descricao_imovel` varchar(255) NOT NULL,
  `preco_imovel` bigint(20) NOT NULL,
  `cep_imovel` varchar(255) NOT NULL,
  `numero_imovel` varchar(255) NOT NULL,
  `rua_imovel` varchar(255) NOT NULL,
  `codigo_bairro_imovel` bigint(20) NOT NULL,
  `codigo_cidade_imovel` bigint(20) NOT NULL,
  `area_imovel` varchar(255) NOT NULL,
  `destaque_imovel` tinyint(1) NOT NULL,
  `status_imovel` varchar(255) NOT NULL,
  `especificacao_imovel` varchar(255) NOT NULL,
  `tipo_imovel` varchar(255) NOT NULL,
  `data_atualizacao_imovel` datetime NOT NULL,
  `data_criacao_imovel` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `galpao` (
  `codigo_imovel` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `casa` (
  `codigo_imovel` bigint(20) NOT NULL,
  `quantidade_quartos_casa` bigint(20) NOT NULL,
  `quantidade_salas_casa` bigint(20) NOT NULL,
  `quantidade_cozinhas_casa` bigint(20) NOT NULL,
  `quantidade_banheiros_casa` bigint(20) NOT NULL,
  `quantidade_garagens_casa` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `apartamento` (
  `codigo_imovel` bigint(20) NOT NULL,
  `quantidade_quartos_apartamento` bigint(20) NOT NULL,
  `quantidade_salas_apartamento` bigint(20) NOT NULL,
  `quantidade_cozinhas_apartamento` bigint(20) NOT NULL,
  `quantidade_banheiros_apartamento` bigint(20) NOT NULL,
  `quantidade_garagens_apartamento` bigint(20) NOT NULL,
  `preco_condominio_apartamento` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `terreno` (
  `codigo_imovel` bigint(20) NOT NULL,
  `grau_inclinacao_terreno` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `favorito_imovel_cliente` (
  `codigo_imovel` bigint(20) NOT NULL,
  `codigo_pessoa` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Indexes for table `pessoa`
--

ALTER TABLE `favorito_imovel_cliente`
  ADD PRIMARY KEY (`codigo_imovel`, `codigo_pessoa`);

--
-- Indexes for table `imovel`
--

ALTER TABLE `imovel`
  ADD PRIMARY KEY (`codigo_imovel`);

ALTER TABLE `galpao`
  ADD PRIMARY KEY (`codigo_imovel`);

ALTER TABLE `casa`
  ADD PRIMARY KEY (`codigo_imovel`);

ALTER TABLE `apartamento`
  ADD PRIMARY KEY (`codigo_imovel`);

ALTER TABLE `terreno`
  ADD PRIMARY KEY (`codigo_imovel`);

ALTER TABLE `cidade`
  ADD PRIMARY KEY (`codigo_cidade`);

ALTER TABLE `bairro`
  ADD PRIMARY KEY (`codigo_bairro`);

--

ALTER TABLE `pessoa`
  ADD PRIMARY KEY (`codigo_pessoa`);

ALTER TABLE `administrador`
  ADD PRIMARY KEY (`codigo_pessoa`);

ALTER TABLE `cliente`
  ADD PRIMARY KEY (`codigo_pessoa`);

--

ALTER TABLE `favorito_imovel_cliente`
  ADD KEY `FK4cm1kg523jlopyexjbmi6y54b` (`codigo_imovel`);

ALTER TABLE `favorito_imovel_cliente`
  ADD KEY `FK4cm1kg523jlopyexjbmi6y54a` (`codigo_pessoa`);

ALTER TABLE `casa`
  ADD KEY `FK4cm1kg523jlopyexjbmi6y53a` (`codigo_imovel`);

ALTER TABLE `apartamento`
  ADD KEY `FK4cm1kg523jlopyexjbmi6y53b` (`codigo_imovel`);

ALTER TABLE `galpao`
  ADD KEY `FK4cm1kg523jlopyexjbmi6y53c` (`codigo_imovel`);

ALTER TABLE `terreno`
  ADD KEY `FK4cm1kg523jlopyexjbmi6y53d` (`codigo_imovel`);

ALTER TABLE `administrador`
  ADD KEY `FK4cm1kg523jlopyexttmi6y54a` (`codigo_pessoa`);

ALTER TABLE `cliente`
  ADD KEY `FK4cm1kg523jlopyzajbmi6y54a` (`codigo_pessoa`);

ALTER TABLE `cidade`
  ADD KEY `FK4cm1kg523654pyexjbmi6y53d` (`codigo_cidade`);

ALTER TABLE `bairro`
  ADD KEY `FK4cm1kg523jl681exjbmi6y53d` (`codigo_bairro`);

ALTER TABLE `bairro`
  ADD KEY `FK4cm1kg523jl681exzzmi6y53d` (`codigo_cidade_bairro`);

-- AUTO_INCREMENT for table `imovel`
--
ALTER TABLE `imovel`
  MODIFY `codigo_imovel` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

ALTER TABLE `pessoa`
  MODIFY `codigo_pessoa` bigint(20) NOT NULL AUTO_INCREMENT;

ALTER TABLE `cidade`
  MODIFY `codigo_cidade` bigint(20) NOT NULL AUTO_INCREMENT;

ALTER TABLE `bairro`
  MODIFY `codigo_bairro` bigint(20) NOT NULL AUTO_INCREMENT;

-- Constraints for table `imoveis`

ALTER TABLE `casa`
  ADD CONSTRAINT `FK4cm1kg523jlopyexjbmi6y53a` FOREIGN KEY (`codigo_imovel`) REFERENCES `imovel` (`codigo_imovel`);

ALTER TABLE `apartamento`
  ADD CONSTRAINT `FK4cm1kg523jlopyexjbmi6y53b` FOREIGN KEY (`codigo_imovel`) REFERENCES `imovel` (`codigo_imovel`);

ALTER TABLE `galpao`
  ADD CONSTRAINT `FK4cm1kg523jlopyexjbmi6y53c` FOREIGN KEY (`codigo_imovel`) REFERENCES `imovel` (`codigo_imovel`);

ALTER TABLE `terreno`
  ADD CONSTRAINT `FK4cm1kg523jlopyexjbmi6y53d` FOREIGN KEY (`codigo_imovel`) REFERENCES `imovel` (`codigo_imovel`);

ALTER TABLE `favorito_imovel_cliente`
  ADD CONSTRAINT `FK4cm1kg523jlopyexjbmi6y54b` FOREIGN KEY (`codigo_imovel`) REFERENCES `imovel` (`codigo_imovel`);

ALTER TABLE `favorito_imovel_cliente`
  ADD CONSTRAINT `FK4cm1kg523jlopyexjbmi6y54a` FOREIGN KEY (`codigo_pessoa`) REFERENCES `cliente` (`codigo_pessoa`);

ALTER TABLE `administrador`
  ADD CONSTRAINT `FK4cm1kg523jlopyexttmi6y54a` FOREIGN KEY (`codigo_pessoa`) REFERENCES `pessoa` (`codigo_pessoa`);

  ALTER TABLE `cliente`
  ADD CONSTRAINT `FK4cm1kg523jlopyzajbmi6y54a` FOREIGN KEY (`codigo_pessoa`) REFERENCES `pessoa` (`codigo_pessoa`);

ALTER TABLE `imovel`
  ADD CONSTRAINT `FK4cm1kg523654pyexjbmi6y53d` FOREIGN KEY (`codigo_cidade_imovel`) REFERENCES `cidade` (`codigo_cidade`);

ALTER TABLE `imovel`
  ADD CONSTRAINT `FK4cm1kg523jl681exjbmi6y53d` FOREIGN KEY (`codigo_bairro_imovel`) REFERENCES `bairro` (`codigo_bairro`);

ALTER TABLE `bairro`
  ADD CONSTRAINT `FK4cm1kg523jl681exzzmi6y53d` FOREIGN KEY (`codigo_cidade_bairro`) REFERENCES `cidade` (`codigo_cidade`);
