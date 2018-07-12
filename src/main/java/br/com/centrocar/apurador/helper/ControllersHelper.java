package br.com.centrocar.apurador.helper;

import java.time.LocalDate;

import br.com.centrocar.apurador.dao.DevolucaoDAO;
import br.com.centrocar.apurador.dao.NotaFiscalConsumidorDAO;
import br.com.centrocar.apurador.dao.NotaFiscalDAO;
import br.com.centrocar.apurador.dao.SemValorFiscalDAO;
import br.com.centrocar.apurador.dao.TotalDeVendasDAO;
import br.com.centrocar.apurador.dao.VendedorDAO;
import br.com.centrocar.apurador.modelo.Devolucao;
import br.com.centrocar.apurador.modelo.NotaFiscal;
import br.com.centrocar.apurador.modelo.NotaFiscalConsumidor;
import br.com.centrocar.apurador.modelo.SemValorFiscal;
import br.com.centrocar.apurador.modelo.TotalDeVendas;
import br.com.centrocar.apurador.modelo.Venda;
import br.com.centrocar.apurador.modelo.Vendedor;

public abstract class ControllersHelper implements AplicationHelper{
	
	protected final Devolucao devolucao = new Devolucao();
	protected final NotaFiscal nota = new NotaFiscal();
	protected final NotaFiscalConsumidor notaFiscalConsumidor = new NotaFiscalConsumidor();
	protected final SemValorFiscal semValorFiscal = new SemValorFiscal();
	protected final TotalDeVendas totalDeVendas = new TotalDeVendas();
	protected final Vendedor vendedor = new Vendedor();
	
	/**
     * Pega o resultado da consulta realizada ao banco de dados e retorna os valares para uma devolução.
     * Recebe como parâmetros uma {@link Venda} e um {@link Vendedor}.
     * 
     * @param venda
     * @param vendedor
     * @return Retorna uma {@link Devolucao}.
     * @throws Lança uma Exception
     */
	@Override
	public Devolucao pegaDevolucao(Vendedor vendedor, LocalDate dataInicial, 
			LocalDate dataFinal) throws Exception {
		
		DevolucaoDAO daoDevolucoes = new DevolucaoDAO();
		devolucao.setDataInicial(dataInicial);
		devolucao.setDataFinal(dataFinal);
		devolucao.setVendedor(vendedor);
		devolucao.getVendedor().setFilial(vendedor.getFilial());
		devolucao.getVendedor().setCodigo(vendedor.getCodigo());
		Devolucao dev = (Devolucao) daoDevolucoes.seleciona(devolucao);
		return dev;
	}
	
	/**
     * Pega o resultado da consulta realizada ao banco de dados e retorna os valares para uma nf.
     * Recebe como parâmetros uma {@link Venda} e um {@link Vendedor}.
     * 
     * @param venda
     * @param vendedor
     * @return Retorna uma {@link NotaFiscal}.
     * @throws Lança uma Exception
     */
	@Override
	public NotaFiscal pegaNotaFiscal(Vendedor vendedor, LocalDate dataInicial, 
			LocalDate dataFinal) throws Exception {
		
		NotaFiscalDAO daoNF = new NotaFiscalDAO();
        nota.setDataInicial(dataInicial);
        nota.setDataFinal(dataFinal);
        nota.setVendedor(vendedor);
        nota.getVendedor().setFilial(vendedor.getFilial());
        nota.getVendedor().setCodigo(vendedor.getCodigo());
        NotaFiscal nf = (NotaFiscal) daoNF.seleciona(nota);
        return nf;
	}
	
	/**
     * Pega o resultado da consulta realizada ao banco de dados e retorna os valares para uma nfc.
     * Recebe como parâmetros uma {@link Venda} e um {@link Vendedor}.
     * 
     * @param venda
     * @param vendedor
     * @return Retorna uma {@link NotaFiscalConsumidor}.
     * @throws Lança uma Exception
     */
	@Override
	public NotaFiscalConsumidor pegaNotaFiscalConsumidor(Vendedor vendedor, 
			LocalDate dataInicial, LocalDate dataFinal) throws Exception {
		
		NotaFiscalConsumidorDAO daoNFC = new NotaFiscalConsumidorDAO();
        notaFiscalConsumidor .setDataInicial(dataInicial);
        notaFiscalConsumidor.setDataFinal(dataFinal);
        notaFiscalConsumidor.setVendedor(vendedor);
        notaFiscalConsumidor.getVendedor().setFilial(vendedor.getFilial());
        notaFiscalConsumidor.getVendedor().setCodigo(vendedor.getCodigo());
        NotaFiscalConsumidor nfc = (NotaFiscalConsumidor) daoNFC.seleciona(notaFiscalConsumidor);
        return nfc;
	}
	
	/**
     * Pega o resultado da consulta realizada ao banco de dados e retorna os valares para uma venda sem valor fiscal.
     * Recebe como parâmetros uma {@link Venda} e um {@link Vendedor}.
     * 
     * @param venda
     * @param vendedor
     * @return Retorna uma venda {@link SemValorFiscal}.
     * @throws Lança uma Exception
     */
	@Override
	public SemValorFiscal pegaSemValorFiscal(Vendedor vendedor, LocalDate dataInicial, 
			LocalDate dataFinal) throws Exception {
		
		SemValorFiscalDAO daoSVF = new SemValorFiscalDAO();
        semValorFiscal.setDataInicial(dataInicial);
        semValorFiscal.setDataFinal(dataFinal);
        semValorFiscal.setVendedor(vendedor);
        semValorFiscal.getVendedor().setFilial(vendedor.getFilial());
        semValorFiscal.getVendedor().setCodigo(vendedor.getCodigo());
        SemValorFiscal svfValor = (SemValorFiscal) daoSVF.seleciona(semValorFiscal);
        return svfValor;
	}
	
	/**
     * Pega o resultado da consulta realizada ao banco de dados e retorna os valares para o total de todas as vendas.
     * Recebe como parâmetros uma {@link Venda} e um {@link Vendedor}.
     * 
     * @param venda
     * @param vendedor
     * @return Retorna um {@link TotalDeVendas}.
     * @throws Lança uma Exception
     */
	@Override
	public TotalDeVendas pegaTotalDeVendas(Vendedor vendedor, LocalDate dataInicial, 
			LocalDate dataFinal) throws Exception {
		
		TotalDeVendasDAO daoTotal = new TotalDeVendasDAO();
        totalDeVendas.setDataInicial(dataInicial);
        totalDeVendas.setDataFinal(dataFinal);
        totalDeVendas.setVendedor(vendedor);
        totalDeVendas.getVendedor().setFilial(vendedor.getFilial());
        totalDeVendas.getVendedor().setCodigo(vendedor.getCodigo());
        TotalDeVendas totalVendas = (TotalDeVendas) daoTotal.seleciona(totalDeVendas);
        return totalVendas;
	}
	
	/**
	 * Pega o usuário através da consulta ao banco de dados, usuando como parâmetro o valor digitado no {@code txUsuario}
	 * @return Um {@link Vendedor}.
	 * @throws Lança uma {@link Exception}.
	 */
	@Override
	public Vendedor pegaVendedor(String tx) throws Exception {
		VendedorDAO dao = new VendedorDAO();
        vendedor.setDescricao(tx);
        Vendedor v = dao.seleciona(vendedor);
        return v;
	}
}
