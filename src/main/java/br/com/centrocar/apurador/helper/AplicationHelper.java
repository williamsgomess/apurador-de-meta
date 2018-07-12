package br.com.centrocar.apurador.helper;

import java.time.LocalDate;

import br.com.centrocar.apurador.modelo.Devolucao;
import br.com.centrocar.apurador.modelo.NotaFiscal;
import br.com.centrocar.apurador.modelo.NotaFiscalConsumidor;
import br.com.centrocar.apurador.modelo.SemValorFiscal;
import br.com.centrocar.apurador.modelo.TotalDeVendas;
import br.com.centrocar.apurador.modelo.Venda;
import br.com.centrocar.apurador.modelo.Vendedor;

public interface AplicationHelper {
	
	/**
     * Pega o resultado da consulta realizada ao banco de dados e retorna os valares para uma devolução.
     * Recebe como parâmetros uma {@link Venda} e um {@link Vendedor}.
     * 
     * @param venda
     * @param vendedor
     * @return Retorna uma {@link Devolucao}.
     * @throws Lança uma Exception
     */
	Devolucao pegaDevolucao(Vendedor vendedor, LocalDate dataInicial, 
			LocalDate dataFinal) throws Exception;
	
	/**
     * Pega o resultado da consulta realizada ao banco de dados e retorna os valares para uma nf.
     * Recebe como parâmetros uma {@link Venda} e um {@link Vendedor}.
     * 
     * @param venda
     * @param vendedor
     * @return Retorna uma {@link NotaFiscal}.
     * @throws Lança uma Exception
     */
	NotaFiscal pegaNotaFiscal(Vendedor vendedor, LocalDate dataInicial, 
			LocalDate dataFinal) throws Exception;
	
	/**
     * Pega o resultado da consulta realizada ao banco de dados e retorna os valares para uma nfc.
     * Recebe como parâmetros uma {@link Venda} e um {@link Vendedor}.
     * 
     * @param venda
     * @param vendedor
     * @return Retorna uma {@link NotaFiscalConsumidor}.
     * @throws Lança uma Exception
     */
	NotaFiscalConsumidor pegaNotaFiscalConsumidor(Vendedor vendedor, 
			LocalDate dataInicial, LocalDate dataFinal) throws Exception;
	
	/**
     * Pega o resultado da consulta realizada ao banco de dados e retorna os valares para uma venda sem valor fiscal.
     * Recebe como parâmetros uma {@link Venda} e um {@link Vendedor}.
     * 
     * @param venda
     * @param vendedor
     * @return Retorna uma venda {@link SemValorFiscal}.
     * @throws Lança uma Exception
     */
	SemValorFiscal pegaSemValorFiscal(Vendedor vendedor, LocalDate dataInicial, 
			LocalDate dataFinal) throws Exception;
	
	/**
     * Pega o resultado da consulta realizada ao banco de dados e retorna os valares para o total de todas as vendas.
     * Recebe como parâmetros uma {@link Venda} e um {@link Vendedor}.
     * 
     * @param venda
     * @param vendedor
     * @return Retorna um {@link TotalDeVendas}.
     * @throws Lança uma Exception
     */
	TotalDeVendas pegaTotalDeVendas(Vendedor vendedor, LocalDate dataInicial, 
			LocalDate dataFinal) throws Exception;
	
	/**
	 * Pega o usuário através da consulta ao banco de dados, usuando como parâmetro o valor digitado no {@code txUsuario}
	 * @return Um {@link Vendedor}.
	 * @throws Lança uma {@link Exception}.
	 */
	Vendedor pegaVendedor(String tx) throws Exception;
}
