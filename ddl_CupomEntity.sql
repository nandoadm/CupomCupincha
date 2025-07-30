
CREATE TABLE public.cupom
(
    id           UUID                        NOT NULL,
    descricao    VARCHAR(255),
    titulo       VARCHAR(255),
    codigo       VARCHAR(50)                 NOT NULL,
    validade     TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    desconto     DECIMAL(10, 2),
    restricoes   VARCHAR(255),
    created_at   TIMESTAMP WITHOUT TIME ZONE,
    categoria_id UUID,
    loja_id      UUID,
    afiliado_id  UUID,
    ativo        INTEGER,
    CONSTRAINT pk_cupom PRIMARY KEY (id)
);

ALTER TABLE public.cupom
    ADD CONSTRAINT FK_CUPOM_ON_AFILIADO FOREIGN KEY (afiliado_id) REFERENCES public.afiliado (id);

ALTER TABLE public.cupom
    ADD CONSTRAINT FK_CUPOM_ON_CATEGORIA FOREIGN KEY (categoria_id) REFERENCES public.categoria (id);

ALTER TABLE public.cupom
    ADD CONSTRAINT FK_CUPOM_ON_LOJA FOREIGN KEY (loja_id) REFERENCES public.loja (id);