import javax.swing.*;
import java.awt.event.*;
import java.awt.*;


public class Os extends JFrame implements ActionListener
{
	JLabel l[],p[],a1[],c[];
	JTextField t1[][],t2[];
	JButton b[] = new JButton[3];
	Container con;
	int k1,k2;
	JPanel  jp;
	Font f = new Font("Arial",Font.BOLD,14);

	public void setComponents1()
	{
		con = getContentPane();
		k1 = Integer.parseInt(JOptionPane.showInputDialog("Enter number of Process"));
		k2 = Integer.parseInt(JOptionPane.showInputDialog("Enter number of Resource"));
		
		l = new JLabel[k1+k1+k2+k2+k2+k2+3];
		p = new JLabel[k2+k2+2];
		c = new JLabel[k2+k2+2];
		a1 = new JLabel[k2+2];
		t2 = new JTextField[k2];
		t1 = new JTextField[k1][k2+k2];

		l[0] = new JLabel();
		b[0] = new JButton("NEXT >>");
        b[1] = new JButton("CLEAR");
        b[2] = new JButton("RESET");
        b[0].setFont(f);
        b[1].setFont(f);
        b[2].setFont(f);

        	for(int i=0;i<(k2+k2+2);i++)
		{
			p[i] = new JLabel();
		}
		for(int i=0;i<(k2+k2+2);i++)
		{
			c[i] = new JLabel();
		}
		for(int i=0;i<k2;i++)
		{
			a1[i] = new JLabel("    Resource "+(i+1));
		}
		a1[k2] = new JLabel();
		a1[k2+1] = new JLabel();
		for(int i=0;i<k2;i++)
		{
			t2[i] = new JTextField(10);
		}

		if(k2%2!=0)
		{
			int o=k2/2+1;
			p[o].setText(""+"  Allocated");
			p[o].setFont(f);
			o+=k2+1;
			p[o].setText(""+"  Max Need");
			p[o].setFont(f);
		}
		else
		{
			int o=k2/2;
			p[o].setText(""+"  Allocated");
			p[o].setFont(f);
			o+=k2+1;
			p[o].setText(""+"  Max Need");
			p[o].setFont(f);
		}

		int a=1;
		for(int i=1;i<=k2;i++)
		{
			l[a] = new JLabel("    Resource "+i);
			a++;
		}
		l[a] = new JLabel();
		a++;
		for(int i=1;i<=k2;i++)
		{
			l[a] = new JLabel("    Resource "+i);
			a++;
		}
		for(int i=1;i<=k1;i++)
		{
			l[a] = new JLabel("      Process "+i);
			a++;
			l[a] = new JLabel();
			a++;
		}
		for(int i=1;i<=k2;i++)
		{
			l[a] = new JLabel();
			a++;
		}
		for(int i=1;i<=k2+1;i++)
		{
			l[a] = new JLabel();
			a++;
		}

		for(int i=0;i<k1;i++)
		{
			for(int j=0;j<k2;j++)
			{
				t1[i][j] = new JTextField(10);
			}
			for(int j=k2;j<k2+k2;j++)
			{
				t1[i][j] = new JTextField(10);
			}
		}

		b[0].addActionListener(this);
		b[1].addActionListener(this);
		b[2].addActionListener(this);

		con.add(a1[k2]);
		for(int i=0;i<k2;i++)
		{
			con.add(a1[i]);
			con.add(t2[i]);
		}
		con.add(a1[k2+1]);
		for(int i=0;i<(k2+k2+2);i++)
		{
			con.add(c[i]);
		}
		for(int i=0;i<(k2+k2+2);i++)
		{
			con.add(p[i]);
		}

		con.add(l[0]);
		a=1;
		for(int i=1;i<=k2;i++)
		{
			con.add(l[a]);
			a++;
		}
    	con.add(l[a]);
		a++;
		for(int i=1;i<=k2;i++)
		{
			con.add(l[a]);
			a++;
		}
		for(int i=0;i<k1;i++)
		{
			con.add(l[a]);
			a++;
			for(int j=0;j<k2;j++)
			{
				con.add(t1[i][j]);
			}
			con.add(l[a]);
			a++;
			for(int j=k2;j<k2+k2;j++)
			{
				con.add(t1[i][j]);
			}
		}
		for(int i=1;i<=k2;i++)
		{
			con.add(l[a]);
			a++;
		}
		for(int i=1;i<k2;i++)
		{
			con.add(l[a]);
			a++;
		}	
		con.add(b[0]);
		con.add(b[1]);
		con.add(b[2]);	
		con.setLayout(new GridLayout(k1+5,k2)); 
	}

	public Os()
	{}
	public Os(String s)
	{
		super(s);
	}

	public void actionPerformed(ActionEvent e)
	{
		jp = new JPanel();
        jp.setLayout(new GridLayout(1,k2+1));

		if(e.getSource() == b[0])
		{
			int[] x=new int[k2];
			int[] s=new int[k2];
			int[][] g=new int[k1][k2];
			int[] r=new int[k1];
			String[] n=new String[k1];
			

			for(int i=0;i<k2;i++)
			{
				x[i]=Integer.parseInt(t2[i].getText());
			}
			for(int j=0;j<k2;j++)
			{
				for(int i=0;i<k1;i++)
				{
						s[j]+=Integer.parseInt(t1[i][j].getText());
				}
			}

			for(int i=0;i<k2;i++)
			{
				s[i]=x[i]-s[i];
			}

			for(int i=0;i<k1;i++)
			{
				for(int j=0;j<k2;j++)
				{
					g[i][j]=Integer.parseInt(t1[i][j+k2].getText())-Integer.parseInt(t1[i][j].getText());
				}
			}
			for(int j=0;j<k1;j++)
			{
				r[j] = -1;
			}
			int q=0,v=0;
			while(true)
			{
				for(int i=0;i<k1;i++)
				{
					int b=0;
					for(int j=0;j<k1;j++)
					{
						if(r[j]==i)
							b=1;
					}
					if(b==1)
						continue;
					int f=0;
					for(int j=0;j<k2;j++)
					{
						if(s[j]>=g[i][j])
						{
							f++;
						}
					}
					if(f==k2)
					{
						r[v]=i;
						v++;
						for(int j=0;j<k2;j++)
						{
							s[j]+=Integer.parseInt(t1[i][j].getText());
						}
						break;
					}
				}
				q++;
				if(q>10)
					break;
			}
			for (int i=0;i<(k1-1);i++) 
			{
				jp.add(new JLabel("Process "+(r[i]+1)+"  >>  "));
			}
			jp.add(new JLabel("Process "+(r[k1-1]+1)));
			JOptionPane.showMessageDialog(this,jp,"output",JOptionPane.PLAIN_MESSAGE);
		}

		if(e.getSource() == b[1])
		{
			for(int i=0;i<k2;i++)
			{
				t2[i].setText(null);
			}
			for(int i=0;i<k1;i++)
			{
				for(int j=0;j<(k2+k2);j++)
				{
					t1[i][j].setText(null);
				}
			}
		}
		if(e.getSource() == b[2])
		{
			setVisible(false);
			Os f1=new Os("Banker's Algorithm");
			f1.setComponents1();
			f1.setSize(1000,300);
			f1.setVisible(true);
			f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
	}

	public static void main(String[] args)
	{
		Os f1=new Os("Banker's Algorithm");
		f1.setComponents1();
		f1.setSize(800,300);
		f1.setVisible(true);
		f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}



