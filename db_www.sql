USE [master]
GO
/****** Object:  Database [lap_store]    Script Date: 01/11/2022 1:47:25 PM ******/
CREATE DATABASE [lap_store]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'lap_store', FILENAME = N'D:\DB\lap_store.mdf' , SIZE = 12352KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'lap_store_log', FILENAME = N'D:\DB\lap_store_log.ldf' , SIZE = 3200KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [lap_store] SET COMPATIBILITY_LEVEL = 110
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [lap_store].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [lap_store] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [lap_store] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [lap_store] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [lap_store] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [lap_store] SET ARITHABORT OFF 
GO
ALTER DATABASE [lap_store] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [lap_store] SET AUTO_CREATE_STATISTICS ON 
GO
ALTER DATABASE [lap_store] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [lap_store] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [lap_store] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [lap_store] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [lap_store] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [lap_store] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [lap_store] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [lap_store] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [lap_store] SET  DISABLE_BROKER 
GO
ALTER DATABASE [lap_store] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [lap_store] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [lap_store] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [lap_store] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [lap_store] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [lap_store] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [lap_store] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [lap_store] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [lap_store] SET  MULTI_USER 
GO
ALTER DATABASE [lap_store] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [lap_store] SET DB_CHAINING OFF 
GO
ALTER DATABASE [lap_store] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [lap_store] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
USE [lap_store]
GO
/****** Object:  Table [dbo].[lap_categories]    Script Date: 01/11/2022 1:47:26 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[lap_categories](
	[lap_category_id] [int] IDENTITY(1,1) NOT NULL,
	[cover_photo] [text] NOT NULL,
	[import_date] [date] NOT NULL,
	[long_description] [nvarchar](max) NOT NULL,
	[name] [nvarchar](max) NOT NULL,
	[short_description] [nvarchar](255) NOT NULL,
	[size] [varchar](80) NOT NULL,
	[weight] [real] NOT NULL,
	[brand_id] [int] NOT NULL,
 CONSTRAINT [PK__lap_cate__19929B442136B14A] PRIMARY KEY CLUSTERED 
(
	[lap_category_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[lap_images]    Script Date: 01/11/2022 1:47:26 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[lap_images](
	[lap_image_id] [int] IDENTITY(1,1) NOT NULL,
	[image] [text] NOT NULL,
	[lap_id] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[lap_image_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
/****** Object:  Table [dbo].[lap_reviews]    Script Date: 01/11/2022 1:47:26 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[lap_reviews](
	[lap_review_id] [int] IDENTITY(1,1) NOT NULL,
	[comment] [nvarchar](500) NOT NULL,
	[review_date] [datetime] NOT NULL,
	[star] [int] NOT NULL,
	[lap_id] [int] NOT NULL,
	[customer_id] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[lap_review_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[laps]    Script Date: 01/11/2022 1:47:26 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[laps](
	[lap_id] [int] IDENTITY(1,1) NOT NULL,
	[color] [nvarchar](80) NOT NULL,
	[price] [money] NOT NULL,
	[quantity] [int] NOT NULL,
	[lap_category_id] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[lap_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[brands]    Script Date: 01/11/2022 1:47:26 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[brands](
	[brand_id] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](255) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[brand_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[cart_details]    Script Date: 01/11/2022 1:47:26 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[cart_details](
	[lap_id] [int] NOT NULL,
	[cart_header_id] [int] NOT NULL,
	[lap_qty] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[lap_id] ASC,
	[cart_header_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[cart_headers]    Script Date: 01/11/2022 1:47:26 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[cart_headers](
	[customer_id] [int] NOT NULL,
	[total_price] [money] NOT NULL,
	[total_quantity] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[customer_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[sale_order_details]    Script Date: 01/11/2022 1:47:26 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[sale_order_details](
	[lap_id] [int] NOT NULL,
	[sale_order_id] [int] NOT NULL,
	[line_total] [money] NOT NULL,
	[order_qty] [int] NOT NULL,
	[unit_price] [money] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[lap_id] ASC,
	[sale_order_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[sale_orders]    Script Date: 01/11/2022 1:47:26 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[sale_orders](
	[sale_order_id] [int] IDENTITY(1,1) NOT NULL,
	[address] [nvarchar](300) NOT NULL,
	[phone] [varchar](10) NOT NULL,
	[comment] [nvarchar](300) NULL,
	[due_date] [datetime] NOT NULL,
	[freight] [money] NOT NULL,
	[order_date] [datetime] NOT NULL,
	[ship_date] [datetime] NULL,
	[status] [tinyint] NOT NULL,
	[sub_total] [money] NOT NULL,
	[tax_vat] [money] NOT NULL,
	[total_due] [money] NOT NULL,
	[customer_id] [int] NOT NULL,
 CONSTRAINT [PK__sale_ord__B224811BCD9969D7] PRIMARY KEY CLUSTERED 
(
	[sale_order_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[users]    Script Date: 01/11/2022 1:47:26 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[users](
	[user_id] [int] IDENTITY(1,1) NOT NULL,
	[address] [nvarchar](300) NULL,
	[avatar] [text] NULL,
	[email] [varchar](255) NOT NULL,
	[enabled] [bit] NOT NULL,
	[first_name] [nvarchar](255) NOT NULL,
	[gender] [nvarchar](3) NULL,
	[last_name] [nvarchar](255) NOT NULL,
	[password] [varchar](255) NOT NULL,
	[phone] [varchar](10) NULL,
	[role] [varchar](5) NOT NULL,
	[username] [varchar](50) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[user_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
SET IDENTITY_INSERT [dbo].[lap_categories] ON 


ALTER TABLE [dbo].[lap_categories]  WITH CHECK ADD  CONSTRAINT [FK3am9bwkb52w1fxhw22u9lg4hu] FOREIGN KEY([brand_id])
REFERENCES [dbo].[brands] ([brand_id])
GO
ALTER TABLE [dbo].[lap_categories] CHECK CONSTRAINT [FK3am9bwkb52w1fxhw22u9lg4hu]
GO
ALTER TABLE [dbo].[lap_images]  WITH CHECK ADD  CONSTRAINT [FK53snqra17fejc782mfr9bg585] FOREIGN KEY([lap_id])
REFERENCES [dbo].[laps] ([lap_id])
GO
ALTER TABLE [dbo].[lap_images] CHECK CONSTRAINT [FK53snqra17fejc782mfr9bg585]
GO
ALTER TABLE [dbo].[lap_reviews]  WITH CHECK ADD  CONSTRAINT [FKmu3hdp8jghwhnphwty0djy2v6] FOREIGN KEY([customer_id])
REFERENCES [dbo].[users] ([user_id])
GO
ALTER TABLE [dbo].[lap_reviews] CHECK CONSTRAINT [FKmu3hdp8jghwhnphwty0djy2v6]
GO
ALTER TABLE [dbo].[lap_reviews]  WITH CHECK ADD  CONSTRAINT [FKtfv9oaxrqc90hyw54g8hrcmwt] FOREIGN KEY([lap_id])
REFERENCES [dbo].[laps] ([lap_id])
GO
ALTER TABLE [dbo].[lap_reviews] CHECK CONSTRAINT [FKtfv9oaxrqc90hyw54g8hrcmwt]
GO
ALTER TABLE [dbo].[laps]  WITH CHECK ADD  CONSTRAINT [FKjebxjvuot7gnespjpobwcuc8i] FOREIGN KEY([lap_category_id])
REFERENCES [dbo].[lap_categories] ([lap_category_id])
GO
ALTER TABLE [dbo].[laps] CHECK CONSTRAINT [FKjebxjvuot7gnespjpobwcuc8i]
GO
ALTER TABLE [dbo].[cart_details]  WITH CHECK ADD  CONSTRAINT [FKjm4yncilrksthcjdjt6tx24t7] FOREIGN KEY([cart_header_id])
REFERENCES [dbo].[cart_headers] ([customer_id])
GO
ALTER TABLE [dbo].[cart_details] CHECK CONSTRAINT [FKjm4yncilrksthcjdjt6tx24t7]
GO
ALTER TABLE [dbo].[cart_details]  WITH CHECK ADD  CONSTRAINT [FKp8q282yh3bih6wc17dqggyryr] FOREIGN KEY([lap_id])
REFERENCES [dbo].[laps] ([lap_id])
GO
ALTER TABLE [dbo].[cart_details] CHECK CONSTRAINT [FKp8q282yh3bih6wc17dqggyryr]
GO
ALTER TABLE [dbo].[cart_headers]  WITH CHECK ADD  CONSTRAINT [FKdlxce8oic5gd0gqearnw5oj9g] FOREIGN KEY([customer_id])
REFERENCES [dbo].[users] ([user_id])
GO
ALTER TABLE [dbo].[cart_headers] CHECK CONSTRAINT [FKdlxce8oic5gd0gqearnw5oj9g]
GO
ALTER TABLE [dbo].[sale_order_details]  WITH CHECK ADD  CONSTRAINT [FKlgxqrrxbbnkld4obatrnakprw] FOREIGN KEY([lap_id])
REFERENCES [dbo].[laps] ([lap_id])
GO
ALTER TABLE [dbo].[sale_order_details] CHECK CONSTRAINT [FKlgxqrrxbbnkld4obatrnakprw]
GO
ALTER TABLE [dbo].[sale_order_details]  WITH CHECK ADD  CONSTRAINT [FKt48vdqc1mmayh1m1qc3b3x8ea] FOREIGN KEY([sale_order_id])
REFERENCES [dbo].[sale_orders] ([sale_order_id])
GO
ALTER TABLE [dbo].[sale_order_details] CHECK CONSTRAINT [FKt48vdqc1mmayh1m1qc3b3x8ea]
GO
ALTER TABLE [dbo].[sale_orders]  WITH CHECK ADD  CONSTRAINT [FKrrat8u489kwbkiame0f1o9hih] FOREIGN KEY([customer_id])
REFERENCES [dbo].[users] ([user_id])
GO
ALTER TABLE [dbo].[sale_orders] CHECK CONSTRAINT [FKrrat8u489kwbkiame0f1o9hih]
GO
USE [master]
GO
ALTER DATABASE [lap_store] SET  READ_WRITE 
GO
