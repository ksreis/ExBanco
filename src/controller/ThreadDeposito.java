package controller;

import java.util.concurrent.Semaphore;

public class ThreadDeposito extends Thread {
	private int xConta;
	private double sConta;
	private double vTransacao;
	private Semaphore limitacao;

	public ThreadDeposito(int xConta, double sConta, double vTransacao, Semaphore limitacao) {
		this.xConta = xConta;
		this.sConta = sConta;
		this.vTransacao = vTransacao;
		this.limitacao = limitacao;
	}
	@Override
	public void run() {
		try {
			limitacao.acquire();
			creditar();
			sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			limitacao.release();
		}
	}
	public void creditar() {
		System.out.format("Conta Nº %d - Saldo Anterior: R$ %.2f Deposito no valor de: R$ %.2f Novo saldo: R$ %.2f%n",
				xConta, sConta, vTransacao, sConta + vTransacao);
		this.sConta += vTransacao;
	}
}