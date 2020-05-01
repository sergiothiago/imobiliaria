CREATE TABLE `administrador` (
  `codigo_administrador` bigint(20) NOT NULL,
  `nome_administrador` varchar(255) NOT NULL,
  `email_administrador` varchar(255) NOT NULL,
  `senha_administrador` varchar(255) NOT NULL,
  `perfil_administrador` varchar(255) NOT NULL,
  `data_atualizacao_administrador` datetime NOT NULL,
  `data_criacao_administrador` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `cliente` (
  `codigo_cliente` bigint(20) NOT NULL,
  `nome_cliente` varchar(255) NOT NULL,
  `email_cliente` varchar(255) NOT NULL,
  `senha_cliente` varchar(255) NOT NULL,
  `perfil_cliente` varchar(255) NOT NULL,
  `data_atualizacao_cliente` datetime NOT NULL,
  `data_criacao_cliente` datetime NOT NULL
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
  `bairro_imovel` varchar(255) NOT NULL,
  `cidade_imovel` varchar(255) NOT NULL,
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
  `codigo_cliente` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Indexes for table `administrador`
--

ALTER TABLE `favorito_imovel_cliente`
  ADD PRIMARY KEY (`codigo_imovel`, `codigo_cliente`);

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

--
-- Indexes for table `administrador`
--

ALTER TABLE `administrador`
  ADD PRIMARY KEY (`codigo_administrador`);

--
-- Indexes for table `cliente`
--

ALTER TABLE `cliente`
  ADD PRIMARY KEY (`codigo_cliente`);

-- Indexes for table `casa`
--

ALTER TABLE `favorito_imovel_cliente`
  ADD KEY `FK4cm1kg523jlopyexjbmi6y54b` (`codigo_imovel`);

ALTER TABLE `favorito_imovel_cliente`
  ADD KEY `FK4cm1kg523jlopyexjbmi6y54a` (`codigo_cliente`);

ALTER TABLE `casa`
  ADD KEY `FK4cm1kg523jlopyexjbmi6y53a` (`codigo_imovel`);

ALTER TABLE `apartamento`
  ADD KEY `FK4cm1kg523jlopyexjbmi6y53b` (`codigo_imovel`);

ALTER TABLE `galpao`
  ADD KEY `FK4cm1kg523jlopyexjbmi6y53c` (`codigo_imovel`);

ALTER TABLE `terreno`
  ADD KEY `FK4cm1kg523jlopyexjbmi6y53d` (`codigo_imovel`);

--
-- AUTO_INCREMENT for table `imovel`
--
ALTER TABLE `imovel`
  MODIFY `codigo_imovel` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--
ALTER TABLE `cliente`
  MODIFY `codigo_cliente` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--
ALTER TABLE `administrador`
  MODIFY `codigo_administrador` bigint(20) NOT NULL AUTO_INCREMENT;

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
  ADD CONSTRAINT `FK4cm1kg523jlopyexjbmi6y54a` FOREIGN KEY (`codigo_cliente`) REFERENCES `cliente` (`codigo_cliente`);