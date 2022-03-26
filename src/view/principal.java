package view;

import java.util.concurrent.Semaphore;

import controller.ThreadDeposito;
import controller.ThreadSaque;

public class principal {

	public static void main(String[] args) {
		Semaphore limitacaoDeposito = new Semaphore(1);
		Semaphore limitacaoSaque = new Semaphore(1);
		for (int i = 0; i < 20; i++) {
			int tipo = (int) (Math.random() * 2);
			int xConta = (int) (Math.random() * 1000);
			double sConta = Math.round((Math.random() * 1000) * 100d) / 100d;
			double vTransacao = Math.round((Math.random() * 100) * 100d) / 100d;
			if (tipo == 0) {
				ThreadDeposito deposito = new ThreadDeposito(xConta, sConta, vTransacao, limitacaoSaque);
				deposito.start();
			} else if (tipo == 1) {
				ThreadSaque saque = new ThreadSaque(xConta, sConta, vTransacao, limitacaoDeposito);
				saque.start();
			}
		}

	}

}