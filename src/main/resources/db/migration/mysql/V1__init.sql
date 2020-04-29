CREATE TABLE `empresa` (
  `id` bigint(20) NOT NULL,
  `cnpj` varchar(255) NOT NULL,
  `data_atualizacao` datetime NOT NULL,
  `data_criacao` datetime NOT NULL,
  `razao_social` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `funcionario` (
  `id` bigint(20) NOT NULL,
  `cpf` varchar(255) NOT NULL,
  `data_atualizacao` datetime NOT NULL,
  `data_criacao` datetime NOT NULL,
  `email` varchar(255) NOT NULL,
  `nome` varchar(255) NOT NULL,
  `perfil` varchar(255) NOT NULL,
  `qtd_horas_almoco` float DEFAULT NULL,
  `qtd_horas_trabalho_dia` float DEFAULT NULL,
  `senha` varchar(255) NOT NULL,
  `valor_hora` decimal(19,2) DEFAULT NULL,
  `empresa_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `lancamento` (
  `id` bigint(20) NOT NULL,
  `data` datetime NOT NULL,
  `data_atualizacao` datetime NOT NULL,
  `data_criacao` datetime NOT NULL,
  `descricao` varchar(255) DEFAULT NULL,
  `localizacao` varchar(255) DEFAULT NULL,
  `tipo` varchar(255) NOT NULL,
  `funcionario_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
  `destaque_imovel` tinyint(255) NOT NULL,
  `data_atualizacao` datetime NOT NULL,
  `data_criacao` datetime NOT NULL
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
  `quantidade_quartos_casa` bigint(20) NOT NULL,
  `quantidade_salas_casa` bigint(20) NOT NULL,
  `quantidade_cozinhas_casa` bigint(20) NOT NULL,
  `quantidade_banheiros_casa` bigint(20) NOT NULL,
  `quantidade_garagens_casa` bigint(20) NOT NULL,
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

--
-- Indexes for table `empresa`
--

ALTER TABLE `empresa`
  ADD PRIMARY KEY (`id`);

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

-- Indexes for table `funcionario`
--
ALTER TABLE `funcionario`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK4cm1kg523jlopyexjbmi6y54j` (`empresa_id`);

--
-- Indexes for table `lancamento`
--
ALTER TABLE `lancamento`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK46i4k5vl8wah7feutye9kbpi4` (`funcionario_id`);

--
-- AUTO_INCREMENT for table `imovel`
--
ALTER TABLE `imovel`
  MODIFY `codigo_imovel` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `empresa`
--
ALTER TABLE `empresa`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `funcionario`
--
ALTER TABLE `funcionario`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `lancamento`
--
ALTER TABLE `lancamento`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;
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

-- Constraints for table `funcionario`
--
ALTER TABLE `funcionario`
  ADD CONSTRAINT `FK4cm1kg523jlopyexjbmi6y54j` FOREIGN KEY (`empresa_id`) REFERENCES `empresa` (`id`);

--
-- Constraints for table `lancamento`
--
ALTER TABLE `lancamento`
  ADD CONSTRAINT `FK46i4k5vl8wah7feutye9kbpi4` FOREIGN KEY (`funcionario_id`) REFERENCES `funcionario` (`id`);

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