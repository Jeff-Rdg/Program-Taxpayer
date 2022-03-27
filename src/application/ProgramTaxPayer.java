package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entities.Company;
import entities.Individual;
import entities.TaxPayer;

/*fazer um programa para ler os dados de n contribuintes(N fornecido pelo usuário),
 * os quais podem ser pesoa fisica ou pessoa juridica, e depois mostrar o valor do imposto
 * pago por cada um, bem como o total de imposto arrecadado.
 * 
 * os dados de pessoa fisica são: nome, renda anual e gastos com saude.
 * os dados de pessoa juridica sao nome, renda anual e numero de funcionarios.
 * 
 * As regras para o calculo de imposto sao as seguintes:
 * Pessoa fisica: péssoas cuja renda foi abaixo de 20000,00 pagam 15% de imposto.
 * Pessoas com renda de 20000,00 em diante pagam 25% de imposto.
 * Se a pessoa teve gastos com saúde, 50% destes gastos são abatidos no imposto.
 * Exemplo: uma pessoa cuja renda foi 50000,00 e teve 2000,00 em gastos com saúde, o 
 * imposto fica (50000*25%) - (2000*50%) = 11500,00
 * 
 * Pessoa juridica: pessoas juridicas pagam 16% de imposto. porém se a empresa possuir
 * mais de 10 funcionários, ela paga 14% de imposto. Exemplo: uma empresa cuja renda foi 4000000,00 e 
 * possui 25 funcionários, o imposto fica 400000*14% = 56000,00*/

public class ProgramTaxPayer {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		Locale.setDefault(Locale.US);
		
		System.out.print("Enter the number of taxpayers: ");
		int n = input.nextInt();
		
		List<TaxPayer> list = new ArrayList<TaxPayer>();
		
		for(int i = 1; i <= n; i++) {
			System.out.printf("Taxpayer #%d data: %n", i);
			System.out.print("Individual or company (i/c)? ");
			char type = input.next().charAt(0);
			System.out.print("Name: ");
			input.nextLine();
			String name = input.nextLine();
			System.out.print("Anual income: ");
			Double anualIncome = input.nextDouble();
			if(type == 'i') {
				System.out.print("Health expenditures: ");
				Double healthExpenditures = input.nextDouble();
				list.add(new Individual(name, anualIncome, healthExpenditures));
			}else {
				System.out.print("Number of employees: ");
				Integer numberOfEmployees = input.nextInt();
				list.add(new Company(name, anualIncome, numberOfEmployees));
			}
		}
		System.out.println();
		System.out.println("TAXES PAID: ");
		for(TaxPayer tPayer : list) {
			System.out.println(tPayer.getName() + " : $" + String.format("%.2f", tPayer.tax()));
		}
		
		System.out.println();
		double sum = 0.0;
		for(TaxPayer tPayer : list) {
			sum += tPayer.tax();
		}
		
		System.out.println("TOTAL TAX: $ " + String.format("%.2f", sum));
		
		input.close();
	}
}
