\NeedsTeXFormat{LaTeX2e}
\ProvidesClass{recenzja}

\LoadClass[12pt,a4paper]{article}

\usepackage[polish]{babel}
\usepackage{graphicx}
\usepackage[normalem]{ulem} % tekst przekre¶lony
\usepackage{times}
%\usepackage[latin2]{inputenc}
\usepackage[utf8]{inputenc}
\usepackage[T1]{fontenc}

\usepackage{anysize}
\marginsize{2cm}{1.5cm}{1cm}{1cm}

%\textwidth 18cm
%\textheight 25cm
%\topmargin -20mm
%\oddsidemargin -10mm

\setlength{\parindent}{0mm}
\setlength{\parskip}{5mm}

\def\jednostka#1{\gdef\@jednostka{#1}}
\def\promotor#1{\gdef\@promotor{#1}}
\def\student#1{\gdef\@student{#1}}
\def\studentnr#1{\gdef\@studentnr{#1}}
\def\temat#1{\gdef\@temat{#1}}
\def\merytA#1{\gdef\@merytA{#1}}
\def\merytB#1{\gdef\@merytB{#1}}
\def\strukt#1{\gdef\@strukt{#1}}
\def\zrodla#1{\gdef\@zrodla{#1}}
\def\formal#1{\gdef\@formal{#1}}
\def\wklad#1{\gdef\@wklad{#1}}
\def\uwagi#1{\gdef\@uwagi{#1}}
\def\ocena#1{\gdef\@ocena{#1}}
\def\rola#1{\gdef\@rola{#1}}
\def\typpracy#1{\gdef\@typpracy{#1}}



\newcommand{\rec}{

\newpage

\pagestyle{empty}

\begin{minipage}{2.5cm}
\includegraphics[width=1.5cm]{agh_znk_wbr_cmyk}
\end{minipage}
\begin{minipage}{15cm}
\begin{center}
{\Large \bf WYDZIAŁ ELEKTROTECHNIKI, AUTOMATYKI,\\[2mm]
INFORMATYKI  i  INŻYNIERII BIOMEDYCZNEJ}
\end{center}
\end{minipage}
\vspace{2mm}
\hrule 
\vspace{1mm}
\hrule

\begin{flushright}
Kraków, \@date
\end{flushright}


\begin{center}
{\huge \bf Ocena\\[4mm] \@typpracy ~przez \@rola{a}} \par % opiekuna
\end{center}

\vspace{8mm}

{{\bf Imię i nazwisko \@rola{a} pracy dyplomowej:} \@author } % opiekuna

{{\bf \@jednostka }}

\begin{minipage}[t]{4.4cm} 
  {\bf Temat pracy/projektu:}
\end{minipage}
\begin{minipage}[t][1.5cm]{15cm}
  \@title
\end{minipage}

{{\bf Imię i nazwisko dyplomanta:} \@student }

{{\bf Numer albumu:} \@studentnr }

\begin{tabular}{|p{172mm}|}
\hline
{\em W przypadku pracy dwuosobowej opiekun pracy dyplomowej w punktach 2 oraz 5 do 8 ocenia wkład pracy każdego z dyplomantów oddzielnie (recenzje nie mogą być identyczne).} \\
\hline 
\end{tabular}

{{\bf 1. Czy treść pracy odpowiada tematowi określonemu w tytule:} \@temat }

\begin{minipage}[t][7.5cm]{\columnwidth} {{\bf 2. Merytoryczna ocena pracy:}
    \@merytA }
\end{minipage}

\newpage

\begin{minipage}[t][2.5cm]{\columnwidth} {
    \@merytB }
\end{minipage}


\begin{minipage}[t][3.5cm]{\columnwidth} 
  {\bf 3. Ocena struktury pracy, kolejności rozdziałów, itp.:} \@strukt
\end{minipage}

\begin{minipage}[t][1.5cm]{\columnwidth} 
  {{\bf 4. Charakterystyka doboru i wykorzystania źródeł:} \@zrodla }
\end{minipage}

\begin{minipage}[t][1.5cm]{\columnwidth} 
  {{\bf 5. Ocena formalnej strony pracy (poprawność języka, strona edytorska, itp.):} \@formal }
\end{minipage}

\begin{minipage}[t][1.5cm]{\columnwidth} 
  {{\bf 6. Ocena indywidualnego wkładu pracy dyplomanta:} \@wklad }
\end{minipage}

\vspace{0.5cm}

{{\bf 7. Uwagi dodatkowe (końcowe):} \@uwagi }

{{\bf 8. Ocena końcowa pracy/projektu:} \@ocena }

\vspace{1.0cm}


\makebox[66mm][s]{\hfill \@date \hfill }\makebox[45mm][s]{}\makebox[66mm][s]{\dotfill}\\
\makebox[66mm][c]{\small \em data}\makebox[45mm][s]{}\makebox[66mm][c]{\small \em podpis}

}


\def\rodzaj{magisterskiej / \sout{inżynierskiej}}

\DeclareOption{inz}{
  \def\rodzaj{\sout{magisterskiej} / inżynierskiej}
}

\ProcessOptions
